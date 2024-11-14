package com.example.fitnessclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Creates a login path used by Spring Security
     * @return login.html
     */
    @GetMapping
    public String login() {
        return "login";
    }
}
