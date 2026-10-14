package com.otsdemo.onlineToletSystemDemo.config;

import com.otsdemo.onlineToletSystemDemo.repository.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Bean UserDetailsService accounts(HouseOwnerRepository owners, UserRepository tenants) {
        return email -> {
            String normalized = email.trim().toLowerCase(java.util.Locale.ROOT);
            var owner = owners.findByEmail(normalized);
            if (owner.isPresent()) return User.withUsername(normalized).password(owner.get().getPassword()).roles("OWNER").build();
            var tenant = tenants.findByUserEmail(normalized).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
            return User.withUsername(normalized).password(tenant.getPassword()).roles("TENANT").build();
        };
    }
    @Bean SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/signin", "/login", "/error", "/css/**", "/images/**", "/houseowner/registration", "/tenant/registration", "/flatlist", "/short", "/female").permitAll()
                .requestMatchers("/houseowner/**", "/houseowner").hasRole("OWNER")
                .requestMatchers("/tenant/**", "/tenant").hasRole("TENANT")
                .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/signin").loginProcessingUrl("/login")
                .successHandler((request,response,authentication) -> response.sendRedirect(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_OWNER")) ? "/houseowner" : "/tenant"))
                .failureUrl("/signin?error").permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/signin?logout"))
            .build();
    }
}
