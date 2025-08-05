package com.otsdemo.onlineToletSystemDemo.controller;

import com.otsdemo.onlineToletSystemDemo.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @Autowired
    HouseOwnerController houseOwnerController;

    @Autowired
    TenantController tenantController;

    @GetMapping("/logout")
    public String logout(){

        houseOwnerController.lg.setLoggedin(false);
        tenantController.tlg.setLoggedin(false);
        return "home";
    }
}
