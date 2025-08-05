package com.otsdemo.onlineToletSystemDemo.controller;

import com.otsdemo.onlineToletSystemDemo.model.*;
import com.otsdemo.onlineToletSystemDemo.repository.FlatRepository;
import com.otsdemo.onlineToletSystemDemo.service.HouseOwnerService;
import com.otsdemo.onlineToletSystemDemo.service.TenantService;
import com.otsdemo.onlineToletSystemDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    FlatRepository flatRepository;

    @Autowired
    UserService userService;

    @Autowired
    TenantService tenantService;

    Login tlg = new Login(false);

//    @GetMapping
//    public String profileHome(){
//        return "tenant";
//    }


    @GetMapping
    public String login(Model model){

        if(tlg.isLoggedin() == true){
             //Done
            return "tenant";
        }
        return "tenantLogin";
    }

    @PostMapping
    //@ResponseBody// No need when add HTML page
    public String login(@ModelAttribute User user, Model model) {

        boolean sameemail = userService.checkEmail(user.getUserEmail());
        boolean samePass = userService.checkPassword(user.getPassword());
        if (sameemail && samePass){

            tlg.setLoggedin(true);

            return "tenant";
        }
        model.addAttribute("msg", "Email or Password not found");
        return "tenantLogin";
    }

    @GetMapping("/addflatmate")
    public String addFlatMate(Model model){

        model.addAttribute("tenant", new Tenant());
        model.addAttribute("flat", flatRepository.findAll());
        return "addFlatMember";
    }

    @GetMapping("/profile")//{id} will also be included
    public String profiledetails(){
        return "Profile";
    }

    @GetMapping("/review")//{id} will also be included
    public String review(){
        return "Review";
    }

    @GetMapping("/delete/{tenantId}")
    public String deleteTenant(@PathVariable Long tenantId){
        tenantService.deleteTenant(tenantId);
        return "removeMsg";
    }

}
