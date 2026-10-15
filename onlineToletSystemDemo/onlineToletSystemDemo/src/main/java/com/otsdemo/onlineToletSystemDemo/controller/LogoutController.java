package com.otsdemo.onlineToletSystemDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** GET confirms intent; Spring Security handles CSRF-protected POST and session invalidation. */
@Controller
public class LogoutController {
    @GetMapping("/logout") public String confirmLogout() { return "logout"; }
}
