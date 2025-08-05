package com.otsdemo.onlineToletSystemDemo.controller;

import com.otsdemo.onlineToletSystemDemo.model.Flat;
import com.otsdemo.onlineToletSystemDemo.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/Flat")
public class FlatController {

    @Autowired
    FlatService flatService;

//    @GetMapping("/update-rent/{tenantId}")
//    public String deleteTenant(){
//
//        return "update-form";
//    }

//    @PostMapping("/update-rent/{Id}")
//    public String deleteTenant(@PathVariable Long id){
//        Object flat = flatService.showFlatById(id);
//        flatService.updateRent((Flat) flat);
//        return "removeMsg";
//    }

}