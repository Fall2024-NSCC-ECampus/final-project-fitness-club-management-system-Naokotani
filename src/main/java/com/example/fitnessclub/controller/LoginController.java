package com.example.fitnessclub.controller;

import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {
    final
    UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
