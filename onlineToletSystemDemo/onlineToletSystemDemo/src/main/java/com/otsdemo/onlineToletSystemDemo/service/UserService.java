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

    public void save(@Valid User user) {
        userRepository.save(user);
    }

    public boolean checkEmail(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    public boolean checkPassword(String password) {
        return userRepository.existsByPassword(password);
    }
}
