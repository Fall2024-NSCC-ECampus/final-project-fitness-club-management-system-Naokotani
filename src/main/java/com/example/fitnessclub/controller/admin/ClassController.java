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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CRUD Controller for ClassDetails entities
 */
@RequestMapping("/admin/")
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
    @PostMapping("class/create")
    public String createClass(@Valid ClassRequest classRequest,
                              BindingResult bindingResult, Model model) {
        model.addAttribute("class",
                classRequest);
        if(bindingResult.hasErrors()) {
            return "admin/class/classForm";
        }
        try {
            classService.createClass(classRequest);
        } catch (TrainerNotFound e) {
            model.addAttribute(
                    "trainerNotFound",
                    e.getMessage());
            return "admin/class/classForm";
        } catch (ClassDetailsNotFound e) {
            model.addAttribute(
                    "classDetailsNotFound",
                    e.getMessage());
            return "admin/class/classForm";
        }
        return "redirect:/admin/classes";
    }

    /**
     * Get the form to create a new {@link ClassDetails}
     * @param model for the view.
     * @return classForm.html
     */
    @GetMapping("class/create")
    public String createClassForm(Model model) {
        model.addAttribute("class", new ClassRequest());
        return "admin/class/classForm";
    }

    /**
     * Get the form to create a new {@link com.example.fitnessclub.model.ClassDate}
     * @param id of the {@link ClassDetails} to add a date to.
     * @param model view
     * @return classDateForm.html
     */
    @GetMapping("class/date/create/{id}")
    public String createClassDateForm(@PathVariable Long id, Model model) {
        model.addAttribute("classDateRequest", new ClassDateRequest());
        model.addAttribute("class", classService.findClassDetailsById(id));
        return "admin/class/classDateForm";
    }

    //TODO validation
    /**
     * Creates a new class date
     * @param classDate Class rate from request
     * @param id id of the class {@link ClassDetails} record to add to.
     * @param model view for model
     * @return classes.html
     */
    @PostMapping("class/date/create/{id}")
    public String createClassDate(@Valid ClassDateRequest classDate, @PathVariable Long id, Model model) {
        classService.createClassDate(classDate, id);
        return "redirect:/admin/classes";
    }

    /**
     * Gets a list of all Classes
     * @param model view model
     * @return classList.html
     */
    @GetMapping("classes")
    public String listClasses(Model model) {
        model.addAttribute("classes", classService.findAll());
        return "admin/class/classList";
    }
}
