package com.example.fitnessclub.controller.trainer;

import com.example.fitnessclub.model.Shift;
import com.example.fitnessclub.model.User;
import com.example.fitnessclub.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/trainer/")
@Controller
public class TrainerController {
    private final ShiftService shiftService;
    private final UserService userService;
    private final AttendanceService attendanceService;
    private final ClassService classService;


    public TrainerController(UserService userService,
                             ShiftService shiftService,
                             AttendanceService attendanceService, ClassService classService) {
        this.shiftService = shiftService;
        this.userService = userService;
        this.attendanceService = attendanceService;
        this.classService = classService;
    }

    /**
     * Creates a new attendance record.
     * @param id of the {@link com.example.fitnessclub.model.ClassDate} to do attendance for.
     * @param model for the view
     * @return attendance.html
     */
    @GetMapping("attendance/create/{id}")
    public String createAttendanceForm(@PathVariable Long id, Model model){
        model.addAttribute("classDateId", id);
        model.addAttribute("members", userService.findMembers());
        return "trainer/attendance";
    }

    /**
     * Creates a new attendance record
     * @param selectedUsers a list of {@link User} to add to the attendance record
     * @param id of the trainer for the attendance record
     * @return shifts.html
     */
    @PostMapping("attendance/create/{id}")
    public String submitSelectedUsers(@RequestParam List<Long> selectedUsers,
                                      @PathVariable Long id) {
        attendanceService.createAttendance(selectedUsers, id);
        return "redirect:../../shifts";
    }

    /**
     * Gets the shifts for a trainer.
     * @param userDetails User details of the logged in trainer to get shifts for
     * @param model for the view
     * @return shifts.html
     */
    @GetMapping("shifts")
    public String trainerShifts(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = userService.findUserByEmail(userDetails.getUsername());
        List<Shift> shifts = shiftService.findShiftsByTrainerId(user.getId());
        model.addAttribute("shifts", shifts);
        return "trainer/shifts";
    }


    /**
     * Gets a list of all class dates.
     * @param model for the view
     * @return classList.html
     */
    @GetMapping("/classes")
    public String trainerClasses(Model model) {
        model.addAttribute("classes", classService.findClassDates());
        return "members/classList";
    }
}
