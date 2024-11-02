package com.example.fitnessclub.controller;

import com.example.fitnessclub.Service.UserRegistrationService;
import com.example.fitnessclub.Service.UserRegistrationServiceImpl;
import com.example.fitnessclub.exceptions.UserExists;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class RegisterController {
    final
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    final UserRegistrationService userRegistrationService = new UserRegistrationServiceImpl();

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userRegistrationService.registerUser(userRepository, user, UserRoles.USER);
            return "welcome";
        } catch (UserExists e) {
            return "register";
        }
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/trainer")
    public String registerTrainer(@ModelAttribute User user, Model model) {
        model.addAttribute("User", user);
        userRegistrationService.registerUser(userRepository, user, UserRoles.TRAINER);
        return "login";
    }

    @GetMapping("/register/trainer")
    public String registrationTrainerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/admin")
    public String registerAdmin(@ModelAttribute User user, Model model) {
        model.addAttribute("User", user);
        userRegistrationService.registerUser(userRepository, user, UserRoles.ADMIN);
        return "login";
    }

    @GetMapping("/register/admin")
    public String registrationAdminForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
