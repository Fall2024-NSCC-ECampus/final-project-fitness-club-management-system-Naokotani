package com.example.fitnessclub.controller.admin;

import com.example.fitnessclub.Service.ClassService;
import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.request.ClassRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClassController {
    public final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping("/admin/class/create")
    public String createClass(@Valid ClassRequest classRequest,
                              BindingResult bindingResult, Model model) {
        model.addAttribute("class",
                classRequest);
        if(bindingResult.hasErrors()) {
            return "classForm";
        }
        try {
            classService.createClass(classRequest);
        } catch (TrainerNotFound e) {
            model.addAttribute(
                    "trainerNotFound",
                    e.getMessage());
            return "classForm";
        } catch (ClassDetailsNotFound e) {
            model.addAttribute(
                    "classDetailsNotFound",
                    e.getMessage());
            return "classForm";
        }
        return "welcome";
    }

    @GetMapping("/admin/class/create")
    public String createClassForm(Model model) {
        model.addAttribute("class", new ClassRequest());
        return "classForm";
    }
}
