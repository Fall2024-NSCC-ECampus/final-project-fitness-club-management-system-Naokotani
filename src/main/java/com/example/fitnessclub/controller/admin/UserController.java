package com.example.fitnessclub.controller.admin;

import com.example.fitnessclub.service.*;
import com.example.fitnessclub.exceptions.EmptyRoleSet;
import com.example.fitnessclub.exceptions.UserExists;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * CRUD Controller for Admin user commands
 */
@Log4j2
@RequestMapping("/admin/")
@Controller
public class UserController {
    private final UserService userService;
    private final UserRegistrationService userRegistrationService;
    private final ClassService classService;

    public UserController(UserService userService,
                          UserRegistrationService userRegistrationService,
                          ClassService classService) {
        this.userService = userService;
        this.userRegistrationService = userRegistrationService;
        this.classService = classService;
    }

    /*
     * Create
     */

    /**
     * Creates a new {@link User}
     * @param userRequest The request with the details to create a user.
     * @param bindingResult The validation result from binding to the class
     * @param model The view model
     * @return welcome.html or register.html on error.
     */
    @PostMapping("user/create")
    public String createUser(@Valid UserRequest userRequest,
                             BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "admin/user/register";
        }
        try {
            userRegistrationService.registerUser(userRequest);
            return "redirect:../users";
        } catch (EmptyRoleSet e) {
            model.addAttribute("user", userRequest);
            model.addAttribute("emptyRoleSet", e.getMessage());
            return "admin/user/register";
        } catch (UserExists e) {
            model.addAttribute("userExists", e.getMessage());
            model.addAttribute("user", userRequest);
            return "admin/user/register";
        }
    }

    /**
     * Gets the form to create a {@link User}
     * @param model the view model
     * @return register.html
     */
    @GetMapping("user/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserRequest());
        return "admin/user/register";
    }

    /*
     * Read
     */

    /**
     * Lists all users in an html table.
     * @param model the mode for the view
     * @return users.html
     */
    @GetMapping("users")
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.findUsers());
        userService.findUsers().forEach(user -> log.info("User id: {}", user.getId()));
        return "admin/user/users";
    }

    /**
     * Lists all trainers
     * @param model view model
     * @return users.html with trainers in an html table.
     */
    @GetMapping("users/trainers")
    public String listTrainers(Model model) {
        model.addAttribute("classes", classService.findAll());
        model.addAttribute("users", userService.findTrainers());
        return "admin/user/trainers";
    }

    /**
     * Lists all trainers
     * @param model view model
     * @return users.html with trainers in an html table.
     */
    @GetMapping("users/members")
    public String listMembers(Model model) {
        model.addAttribute("users", userService.findMembers());
        return "admin/user/users";
    }

    /**
     * Get a user by id.
     * @param id The id of the user to be displayed
     * @param model view model
     * @return users.html
     */
    @GetMapping("users/{id}")
    public String getUserByID(@PathVariable Long id, Model model) {
        List<User> users = new ArrayList<>();
        users.add(userService.findUserById(id));
        model.addAttribute("users", users);
        return "admin/user/users";
    }

    /*
     * Update
     */


    /**
     * Gets a prefilled form for {@link User} details.
     * @param id The ID of the user to do updated
     * @param model the view model
     * @return updateUserForm.html
     */
    @GetMapping("user/update/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("user", userService.findUserById(id));
        return "admin/user/updateUserForm";
    }

    /**
     * Updated a {@link User} with data from updateUserForm.html
     * @param userRequest The request from the user.
     * @param id The idea of the user to be updated
     * @param bindingResult Validation result for binding to the class
     * @param model the view model
     * @return welcome.html or updateUserForm.html on error.
     */
    @PostMapping("user/update/{id}")
    public String updateUser(@Valid UserRequest userRequest,
                             @PathVariable Long id,
                             BindingResult bindingResult,
                             Model model) {
        model.addAttribute("user",
                userRequest);
        if(bindingResult.hasErrors()) {
            return "admin/user/updateUserForm";
        }
        userService.updateMember(userRequest, id);
        return "redirect:/admin/users";
    }

    /*
     * Destroy
     */

    /**
     * Deletes a {@link User} by id.
     * @param id The id of the user to be deleted.
     * @return List of users.
     */
    @PostMapping("user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}

