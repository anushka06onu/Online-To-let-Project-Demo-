package com.otsdemo.onlineToletSystemDemo.controller;

import com.otsdemo.onlineToletSystemDemo.model.*;
import com.otsdemo.onlineToletSystemDemo.repository.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/houseowner")
public class HouseOwnerController {
    private final HouseOwnerRepository owners;
    private final FlatRepository flats;
    private final TenantRepository tenants;
    public HouseOwnerController(HouseOwnerRepository owners, FlatRepository flats, TenantRepository tenants) { this.owners=owners; this.flats=flats; this.tenants=tenants; }
    @InitBinder("flat") void flatFields(WebDataBinder binder) { binder.setAllowedFields("flatNo","rent","rentType","noOfRoom","availableDate","available","contactNumber","description","tenantGender","address"); }
    @InitBinder("tenant") void tenantFields(WebDataBinder binder) { binder.setAllowedFields("name","contactNumber","email","moveInDate","flat"); }
    private HouseOwner owner(Principal principal) { return owners.findByEmail(principal.getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN)); }
    private List<Flat> owned(Principal principal) { Long id=owner(principal).getId(); return flats.findAll().stream().filter(f -> f.getHouseOwner()!=null && id.equals(f.getHouseOwner().getId())).toList(); }
    private Flat requireOwned(Long id, Principal principal) {
        Flat flat=flats.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (flat.getHouseOwner()==null || !owner(principal).getId().equals(flat.getHouseOwner().getId())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return flat;
    }
    @GetMapping public String home(Model model, Principal principal) { model.addAttribute("flats",owned(principal)); return "houseowner"; }
    @GetMapping("/addFlat") public String addFlat(Model model, Principal principal) { model.addAttribute("houseOwner",List.of(owner(principal))); model.addAttribute("flat",new Flat()); return "addFlat"; }
    @PostMapping("/addFlat") public String addFlat(@ModelAttribute("flat") Flat flat, Principal principal) {
        if (flat.getFlatNo()==null || flat.getFlatNo().isBlank() || flat.getRent()<0 || flat.getNoOfRoom()<1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Valid flat details are required");
        flat.setId(null); flat.setHouseOwner(owner(principal)); flat.setOwnername(owner(principal).getName()); flat.setTenants(null); flats.save(flat); return "redirect:/houseowner";
    }
    @GetMapping("/{id}/detail") public String details(@PathVariable Long id, Model model, Principal principal) { model.addAttribute("flat",requireOwned(id,principal)); return "flat-detail"; }
    @GetMapping("/{id}/update") public String members(@PathVariable Long id, Model model, Principal principal) { requireOwned(id,principal); model.addAttribute("tenant",tenants.findByFlatId(id)); return "tenant_list"; }
    @GetMapping("/addtenant") public String addTenant(Model model, Principal principal) { model.addAttribute("tenant",new Tenant()); model.addAttribute("flat",owned(principal)); return "addFlatMember"; }
    @PostMapping("/addtenant") public String addTenant(@ModelAttribute("tenant") @Valid Tenant tenant, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) { model.addAttribute("flat",owned(principal)); return "addFlatMember"; }
        if (tenant.getFlat()==null || tenant.getFlat().getId()==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Choose an owned flat");
        tenant.setFlat(requireOwned(tenant.getFlat().getId(),principal)); tenant.setId(null); tenant.setEmail(tenant.getEmail().trim().toLowerCase(java.util.Locale.ROOT)); tenants.save(tenant); return "redirect:/houseowner";
    }
    @PostMapping("/tenant/{id}/delete") public String deleteTenant(@PathVariable Long id, Principal principal) {
        Tenant tenant=tenants.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (tenant.getFlat()==null) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        requireOwned(tenant.getFlat().getId(),principal); tenants.deleteById(id); return "redirect:/houseowner";
    }
}
