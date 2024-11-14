package com.example.fitnessclub.controller;

import com.example.fitnessclub.model.Role;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.model.UserRoles;
import com.example.fitnessclub.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class IndexController {
    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Directs traffic based on roles of the current session
     * @param userDetails the user details of the currently logged in member
     * @return either index.html or a role dashboard.html
     */
    @GetMapping
    public String index(@AuthenticationPrincipal UserDetails userDetails) {
        User user;
        Set<Role> roles = new HashSet<>();
        if (userDetails != null) {
            user = userService.findUserByEmail(userDetails.getUsername());
            roles = user.getRoles();
        }

        boolean isMember = roles.stream().anyMatch(r -> r.getRole().equals(UserRoles.MEMBER));
        boolean isAdmin = roles.stream().anyMatch(r -> r.getRole().equals(UserRoles.ADMIN));
        boolean isTrainer = roles.stream().anyMatch(r -> r.getRole().equals(UserRoles.TRAINER));

        if (isAdmin) {
            return "admin/dashboard";
        } else if (isTrainer) {
            return "trainer/dashboard";
        } else if (isMember) {
            return "members/dashboard";
        }

        return "index";
    }
}
