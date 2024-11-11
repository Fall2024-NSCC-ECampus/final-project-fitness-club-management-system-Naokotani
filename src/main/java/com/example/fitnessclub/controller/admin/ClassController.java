package com.example.fitnessclub.controller.admin;

import com.example.fitnessclub.service.ClassService;
import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.model.ClassDetails;
import com.example.fitnessclub.dto.ClassDateRequest;
import com.example.fitnessclub.dto.ClassRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * CRUD Controller for ClassDetails entities
 */
@Controller
public class ClassController {
    public final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    /*
     * Create
     */

    /**
     * Create a new {@link ClassDetails}
     * @param classRequest the http request to create a class
     * @param bindingResult result of the validation to bind to class.
     * @param model view model.
     * @return welcome.html or classForm.html on error.
     */
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

    @GetMapping("/admin/class/date/create/{id}")
    public String createClassDateForm(@PathVariable Long id, Model model) {
        model.addAttribute("classDateRequest", new ClassDateRequest());
        model.addAttribute("class", classService.findClassDetails(id));
        return "classDateForm";
    }

    //TODO validation
    @PostMapping("/admin/class/date/create/{id}")
    public String createClassDate(@Valid ClassDateRequest classDate, @PathVariable Long id, Model model) {
        classService.createClassDate(classDate, id);
        return "welcome";
    }

    //TODO what is this?
    @GetMapping("/admin/classes")
    public String listClasses(Model model) {
        model.addAttribute("classes", classService.findAll());
        return "classList";
    }

    //TODO Update methods
    //TODO Destroy methods
}
