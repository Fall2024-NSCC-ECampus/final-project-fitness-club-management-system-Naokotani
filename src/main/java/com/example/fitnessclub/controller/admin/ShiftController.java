package com.example.fitnessclub.controller.admin;

import com.example.fitnessclub.Service.ShiftService;
import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.request.ShiftRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping("/admin/shift/create")
    public String createShift(@Valid ShiftRequest shiftRequest,
                              BindingResult bindingResult, Model model) {
        model.addAttribute("shift",
                shiftRequest);
        if(bindingResult.hasErrors()) {
            return "shiftForm";
        }
        try {
            shiftService.createShift(shiftRequest);
        } catch (TrainerNotFound e) {
            model.addAttribute(
                    "trainerNotFound",
                    e.getMessage());
            return "shiftForm";
        } catch (ClassDetailsNotFound e) {
            model.addAttribute(
                    "classDetailsNotFound",
                    e.getMessage());
            return "shiftForm";
        }
        return "welcome";
    }

    @GetMapping("/admin/shift/create")
    public String createShiftForm(Model model) {
        model.addAttribute("shiftRequest", new ShiftRequest());
        return "shiftForm";
    }
}
