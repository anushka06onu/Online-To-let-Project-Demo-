package com.otsdemo.onlineToletSystemDemo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/tenant")
public class TenantController {
    @GetMapping public String home() { return "tenant"; }
}
