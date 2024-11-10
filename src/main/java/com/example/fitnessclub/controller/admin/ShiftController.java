package com.example.fitnessclub.controller.admin;

import com.example.fitnessclub.Service.ShiftService;
import com.example.fitnessclub.exceptions.ClassDetailsNotFound;
import com.example.fitnessclub.exceptions.TrainerNotFound;
import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.request.ShiftRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * CRUD controller for shits
 */
@Controller
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    /*
     * Create
     */

    /**
     * Creates a new shift
     * @param shiftRequest http request for a new shift.
     * @param id of trainer to add to the shift.
     * @param bindingResult the result of validation from binding to the class.
     * @param model the view model.
     * @return welcome.html or shiftForm.html on error.
     */
    @PostMapping("/admin/shift/create/{id}")
    public String createShift(@Valid ShiftRequest shiftRequest, @PathVariable Long id,
                              BindingResult bindingResult, Model model) {
        model.addAttribute("shift",
                shiftRequest);
        if(bindingResult.hasErrors()) {
            return "shiftForm";
        }
        try {
            shiftService.createShift(shiftRequest, id);
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

    /**
     * Gets the html form to create a shift.
     * @param model The view model.
     * @return shiftForm.html.
     */
    @GetMapping("/admin/shift/create/{id}")
    public String createShiftForm(@PathVariable Long id, Model model) {
        model.addAttribute("classes", shiftService.getClasses());
        model.addAttribute("trainer", id);
        model.addAttribute("shiftRequest", new ShiftRequest());
        return "shiftForm";
    }

    /*
     * Read
     */

    /**
     * List all shifts in an HTML form.
     * @param model view model
     * @return shifts.html
     */
    @GetMapping("/admin/shifts")
    public String listShifts(Model model) {
        model.addAttribute("shifts", shiftService.findAll());
        return "shifts";
    }

    /**
     * List all of a trainers shifts in an HTML form.
     * @param model view model
     * @return shifts.html
     */
    @GetMapping("/admin/shifts/{id}")
    public String listShiftsById(@PathVariable Long id, Model model) {
        List<Shift> shifts = shiftService.findByTrainerId(id);
        model.addAttribute("shifts", shifts);
        return "shifts";
    }

    //TODO Update methods

    /*
     * Destroy
     */

    @DeleteMapping("/admin/shift/delete/{id}")
    public String deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return "redirect:/admin/shifts";
    }

}
