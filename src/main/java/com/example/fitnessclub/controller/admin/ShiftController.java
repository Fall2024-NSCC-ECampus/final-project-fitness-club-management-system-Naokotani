package com.example.fitnessclub.controller.admin;

import com.example.fitnessclub.Service.ClassService;
import com.example.fitnessclub.Service.ClassServiceImpl;
import com.example.fitnessclub.Service.ShiftService;
import com.example.fitnessclub.model.Shift;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD controller for shifts
 */
@Controller
public class ShiftController {
    private final ShiftService shiftService;
    private final ClassService classService;

    public ShiftController(ShiftService shiftService, ClassServiceImpl classServiceImpl) {
        this.shiftService = shiftService;
        this.classService = classServiceImpl;
    }

    /*
     * Create
     */

    /**
     * Creates a new shift
     * @return welcome.html or shiftForm.html on error.
     */
    @PostMapping("/admin/shift/create/")
    public String createShift(@RequestParam("trainerId") Long trainerId,
                              @RequestParam("classId") Long classId,
                              @RequestParam("dateId") Long dateId) {
        shiftService.createShift(trainerId, classId, dateId);
        return "welcome";
    }

    /**
     * Gets the html form to create a shift.
     * @param model The view model.
     * @return shiftForm.html.
     */
    @PostMapping("/admin/shift/form/{id}")
    public String createShiftForm(@PathVariable Long id, @RequestParam Long classId, Model model) {
        model.addAttribute("dates", classService.findClassDates(classId));
        model.addAttribute("trainer", id);
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
    @GetMapping("/admin/shifts/trainer/{id}")
    public String listShifts(@PathVariable Long id, Model model) {
        model.addAttribute("shifts", shiftService.findShiftsByTrainerId(id));
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

    /*
     * Destroy
     */
    @PostMapping("/admin/shift/delete/{id}")
    public String deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return "redirect:/admin/shifts";
    }

}
