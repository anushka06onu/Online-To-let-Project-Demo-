package com.otsdemo.onlineToletSystemDemo.controller;

import com.otsdemo.onlineToletSystemDemo.model.HouseOwner;
import com.otsdemo.onlineToletSystemDemo.model.User;
import com.otsdemo.onlineToletSystemDemo.repository.HouseOwnerRepository;
import com.otsdemo.onlineToletSystemDemo.repository.TenantRepository;
import com.otsdemo.onlineToletSystemDemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    HouseOwnerRepository houseOwnerRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    UserService userService;

    @GetMapping("/houseowner/registration")
    public String houseOwnerRegistration(Model model){
        model.addAttribute("houseOwner", new HouseOwner());
        return "ownerRegistrationForm";
    }
    @PostMapping("/houseowner/registration")
    public String registerHouseOwner(@ModelAttribute("houseOwner") @Valid HouseOwner houseOwner, BindingResult result) {

        if(result.hasErrors()){
            return "ownerRegistrationForm";
        }

        houseOwnerRepository.save(houseOwner);
        System.out.println("Registered Owner: " + houseOwner.getName());
        return "registrationSuccess"; // Redirect to a success page
    }

    @GetMapping("/tenant/registration")
    public String tenantRegistration(Model model){
        model.addAttribute("user", new User());
        return "user-regi-form";
    }

    @PostMapping("/tenant/registration")
    public String registerTenant(@ModelAttribute("user") @Valid User user,
                                 BindingResult result, Model model) {

        if(result.hasErrors()){
            return "user-regi-form";
        }

        boolean paisi = tenantRepository.existsByEmail(user.getUserEmail());

        if(paisi){
            userService.save(user);
            return "registrationSuccess";
        }

        model.addAttribute("msg", "Email does not exixts");
        return "user-regi-form";
    }
}
