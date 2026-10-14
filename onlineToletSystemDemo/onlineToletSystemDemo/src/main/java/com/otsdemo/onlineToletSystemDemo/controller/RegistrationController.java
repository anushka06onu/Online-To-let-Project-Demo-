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

    @Autowired org.springframework.security.crypto.password.PasswordEncoder encoder;
    @Autowired com.otsdemo.onlineToletSystemDemo.repository.UserRepository users;
    @org.springframework.web.bind.annotation.InitBinder("houseOwner") void ownerFields(org.springframework.web.bind.WebDataBinder binder) { binder.setAllowedFields("name","email","phoneNumber","password"); }
    @org.springframework.web.bind.annotation.InitBinder("user") void userFields(org.springframework.web.bind.WebDataBinder binder) { binder.setAllowedFields("userEmail","password"); }

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

        String email=houseOwner.getEmail().trim().toLowerCase(java.util.Locale.ROOT);
        if (houseOwnerRepository.existsByEmail(email) || users.existsByUserEmail(email)) { result.rejectValue("email","duplicate","Account already exists."); return "ownerRegistrationForm"; }
        if (houseOwner.getPassword().length()<8 || houseOwner.getPassword().getBytes(java.nio.charset.StandardCharsets.UTF_8).length>72) { result.rejectValue("password","length","Password must be 8 to 72 bytes."); return "ownerRegistrationForm"; }
        houseOwner.setId(null); houseOwner.setFlat(null); houseOwner.setEmail(email); houseOwner.setPassword(encoder.encode(houseOwner.getPassword()));
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
    public String registerTenant(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("msg", "Tenant self-registration is unavailable until an owner invitation or email verification flow is configured. Contact your house owner.");
        return "user-regi-form";
    }
}
