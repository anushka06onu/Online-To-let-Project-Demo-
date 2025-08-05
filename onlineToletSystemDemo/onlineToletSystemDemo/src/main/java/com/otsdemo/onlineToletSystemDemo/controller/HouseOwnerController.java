package com.otsdemo.onlineToletSystemDemo.controller;

import com.otsdemo.onlineToletSystemDemo.model.Flat;
import com.otsdemo.onlineToletSystemDemo.model.HouseOwner;
import com.otsdemo.onlineToletSystemDemo.model.Login;
import com.otsdemo.onlineToletSystemDemo.model.Tenant;
import com.otsdemo.onlineToletSystemDemo.repository.FlatRepository;
import com.otsdemo.onlineToletSystemDemo.repository.HouseOwnerRepository;
import com.otsdemo.onlineToletSystemDemo.repository.TenantRepository;
import com.otsdemo.onlineToletSystemDemo.service.HouseOwnerService;
import com.otsdemo.onlineToletSystemDemo.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/houseowner")
public class HouseOwnerController {

    @Autowired
    private HouseOwnerService houseOwnerService;

    @Autowired
    private HouseOwnerRepository houseOwnerRepository;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private FlatRepository flatRepository;

    Login lg = new Login(false);

    @GetMapping
    public String login(Model model){

        if(lg.isLoggedin() == true){
            List<Flat> flats = houseOwnerService.showFlatList();//Done
            model.addAttribute("flats", flats); //Done
            return "houseowner";
        }
        return "ownerLogin";
    }

    @PostMapping
    //@ResponseBody// No need when add HTML page
    public String login(@ModelAttribute HouseOwner owner, Model model) {

        boolean sameUserName = houseOwnerService.checkEmail(owner.getEmail());
        boolean samePassword = houseOwnerService.checkPassword(owner.getPassword());
        if (sameUserName && samePassword){

            lg.setLoggedin(true);

            List<Flat> flats = houseOwnerService.showFlatList();//Done
            model.addAttribute("flats", flats); //Done
            return "houseowner";


        }
        model.addAttribute("msg", "Email or Password not found");
        return "ownerLogin";
    }

    @GetMapping("/addFlat")
    //@ResponseBody// No need when add HTML page
    public String addFlat(Model model){
        model.addAttribute("houseOwner", houseOwnerService.showOwners());

        return "addFlat"; // Get the Form Page for Adding Flat
    }
//    @PostMapping("/addFlat")
//    public String addFlat(@ModelAttribute Flat flat, @RequestParam("houseOwner") String houseOwnerId) {
//        // Fetch the HouseOwner by ID
//        HouseOwner houseOwner = houseOwnerRepository.findByName(houseOwnerId);
//
//        // Associate the Flat with the HouseOwner
//        flat.setHouseOwner(houseOwner);
//
//        // Save the Flat
//        flatRepository.save(flat);
//
//        return "redirect:/houseowner/flatList"; // Redirect to a page or confirmation
//    }

    @PostMapping("/addFlat")
    public String addFlat(@ModelAttribute Flat flat){
        houseOwnerService.addFlat(flat);
        return "registrationSuccess";
    }

    @GetMapping("/{id}/detail")//{id} will be included
    public String getById(@PathVariable Long id, Model model){
        Flat flat = houseOwnerService.getdetails(id);
        model.addAttribute("flat", flat);
        return "flat-detail";
    }

    @GetMapping("/{id}/update")//{id} will be included
    public String update(@PathVariable Long id, Model model){
        List<Tenant> tenant = tenantService.showFlatTenat(id);
        model.addAttribute("tenant", tenant);
        return "tenant_list";
    }



    @GetMapping("/addtenant")
    public String addTenant(Model model){

        model.addAttribute("tenant", new Tenant());
        model.addAttribute("flat", flatRepository.findAll());
        return "addFlatMember";
    }
    @PostMapping("/addtenant")
    public String addTenant(@ModelAttribute @Valid Tenant tenant, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("flat", flatRepository.findAll());
            return "addFlatMember";
        }
        tenantRepository.save(tenant);
        return "registrationSuccess";
    }

//    @PostMapping("/{id}/addtenant")
//    public String addTenant(@ModelAttribute Tenant tenant){
//        tenantService.addtenant(tenant);
//        return "redirect:/addFlatMember";
//    }
}
