package com.otsdemo.onlineToletSystemDemo.controller;

import com.otsdemo.onlineToletSystemDemo.model.HouseOwner;
import com.otsdemo.onlineToletSystemDemo.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserRegiController {

    @GetMapping("/tenant/registration")
    public String tenantRegistration(Model model){
        model.addAttribute("user", new User());
        return "user-regi-form";
    }
}
