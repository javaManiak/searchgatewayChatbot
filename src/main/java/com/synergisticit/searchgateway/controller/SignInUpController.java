package com.synergisticit.searchgateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInUpController {
    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/authentication")
    public String authentication() {
        return "authentication";
    }
    
}
