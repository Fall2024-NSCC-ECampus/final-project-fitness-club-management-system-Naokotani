package com.example.fitnessclub.controller.admin;

import com.example.fitnessclub.service.ClassService;
import com.example.fitnessclub.service.ShiftService;
import com.example.fitnessclub.model.Shift;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD controller for shifts
 */
@RequestMapping("/admin/")
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
     * @return welcome.html or shiftForm.html on error.
     */
    @PostMapping("shift/create")
    public String createShift(@RequestParam("trainerId") Long trainerId,
                              @RequestParam("classId") Long classId,
                              @RequestParam("dateId") Long dateId) {
        shiftService.createShift(trainerId, classId, dateId);
        return "redirect:/";
    }

    /**
     * Gets the html form to create a shift.
     * @param model The view model.
     * @return shiftForm.html.
     */
    @GetMapping("shift/form")
    public String createShiftForm(@RequestParam Long classId, @RequestParam Long trainerId, Model model) {
        model.addAttribute("dates", shiftService.findAvailableShifts(classId));
        model.addAttribute("trainerId", trainerId);
        return "admin/shift/shiftForm";
    }

    /*
     * Read
     */

    /**
     * List all shifts in an HTML form.
     * @param model view model
     * @return shifts.html
     */
    @GetMapping("shifts/trainer/{id}")
    public String listShifts(@PathVariable Long id, Model model) {
        model.addAttribute("shifts", shiftService.findShiftsByTrainerId(id));
        return "admin/shift/shifts";
    }

    /**
     * List all of a trainers shifts in an HTML form.
     * @param model view model
     * @return shifts.html
     */
    @GetMapping("shifts/{id}")
    public String listShiftsById(@PathVariable Long id, Model model) {
        List<Shift> shifts = shiftService.findByTrainerId(id);
        model.addAttribute("shifts", shifts);
        return "admin/shift/shifts";
    }

    /*
     * Destroy
     */

    /**
     * Deletes a shift
     * @param id of the shift to be deleted.
     * @return shifts.html
     */
    @PostMapping("shift/delete/{id}")
    public String deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return "redirect:/";
    }
}
