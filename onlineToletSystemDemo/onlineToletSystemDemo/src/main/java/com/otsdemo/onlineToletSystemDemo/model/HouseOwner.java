package com.otsdemo.onlineToletSystemDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Enter your name")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "Enter your email")
    private String email;
    @Column(unique = true)
    @NotBlank(message = "Enter your phone number")
    private String phoneNumber;
    @NotBlank(message = "Enter a strong password")
    private String password;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    @OneToMany(mappedBy = "houseOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flat> flat;

}


