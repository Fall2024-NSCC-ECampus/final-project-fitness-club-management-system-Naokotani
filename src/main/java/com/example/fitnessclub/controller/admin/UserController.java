package com.example.fitnessclub.controller.admin;

import com.example.fitnessclub.Service.UserRegistrationServiceImpl;
import com.example.fitnessclub.Service.UserService;
import com.example.fitnessclub.Service.UserServiceImpl;
import com.example.fitnessclub.exceptions.EmptyRoleSet;
import com.example.fitnessclub.exceptions.UserExists;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.request.UserRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
public class UserController {
    private final UserService userService;
    private final UserRegistrationServiceImpl userRegistrationService;

    public UserController(UserServiceImpl userService, UserRegistrationServiceImpl userRegistrationService) {
        this.userService = userService;
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.findUsers());
        userService.findUsers().forEach(user -> log.info("User id: {}", user.getId()));
        return "users";
    }

    @GetMapping("/admin/user/update/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("user", userService.findUserRequestById(id));
        return "updateUserForm";
    }

    @PostMapping("/admin/user/update/{id}")
    public String updateUser(@Valid UserRequest userRequest,
                              @PathVariable Long id,
                              BindingResult bindingResult,
                              Model model) {
        model.addAttribute("user",
                userRequest);
        if(bindingResult.hasErrors()) {
            return "updateUserForm";
        }
        userService.updateMember(userRequest, id);
        return "welcome";
    }

    @PostMapping("/admin/user/create")
    public String createUser(@Valid UserRequest userRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userRegistrationService.registerUser(userRequest);
            return "welcome";
        } catch (EmptyRoleSet e) {
            model.addAttribute("user", userRequest);
            model.addAttribute("emptyRoleSet", e.getMessage());
            return "register";
        } catch (UserExists e) {
            model.addAttribute("userExists", e.getMessage());
            model.addAttribute("user", userRequest);
            return "register";
        }
    }

    @GetMapping("/admin/user/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserRequest());
        return "register";
    }
}
