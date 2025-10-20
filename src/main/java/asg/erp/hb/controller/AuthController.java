package asg.erp.hb.controller;

import asg.erp.hb.service.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(@AuthenticationPrincipal CustomUserDetails currentUser, Model model) {
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("fullName", currentUser.getFullName());
        model.addAttribute("roles", currentUser.getAuthorities());
        return "dashboard";
    }
}
