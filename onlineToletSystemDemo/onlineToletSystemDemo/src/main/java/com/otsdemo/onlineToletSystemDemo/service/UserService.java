package com.otsdemo.onlineToletSystemDemo.service;

import com.otsdemo.onlineToletSystemDemo.model.User;
import com.otsdemo.onlineToletSystemDemo.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired org.springframework.security.crypto.password.PasswordEncoder encoder;
    public void save(@Valid User user) {
        if (user.getPassword()==null || user.getPassword().length()<8 || user.getPassword().getBytes(java.nio.charset.StandardCharsets.UTF_8).length>72) throw new IllegalArgumentException("Password must be 8 to 72 bytes.");
        user.setId(null); user.setUserEmail(user.getUserEmail().trim().toLowerCase(java.util.Locale.ROOT));
        user.setPassword(encoder.encode(user.getPassword())); userRepository.save(user);
    }

    public boolean checkEmail(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    public boolean checkPassword(String password) {
        return userRepository.existsByPassword(password);
    }
}

