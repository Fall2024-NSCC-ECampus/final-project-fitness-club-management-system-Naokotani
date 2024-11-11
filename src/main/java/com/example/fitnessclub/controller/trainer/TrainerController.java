package com.example.fitnessclub.controller.trainer;

import com.example.fitnessclub.service.*;
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


    public TrainerController(UserService userService,
                             ShiftService shiftService,
                             AttendanceService attendanceService) {
        this.shiftService = shiftService;
        this.userService = userService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("attendance/create/{id}")
    public String createAttendanceForm(@PathVariable Long id, Model model){
        model.addAttribute("classDateId", id);
        model.addAttribute("members", userService.findMembers());
        return "attendance";
    }

    @GetMapping("shifts/{id}")
    public String trainerShifts(@PathVariable Long id, Model model){
        model.addAttribute("shifts", shiftService.findClassDateByTrainerId(id));
        return "trainerClasses";
    }

    @PostMapping("attendance/create/{id}")
    public String submitSelectedUsers(@RequestParam List<Long> selectedUsers,
                                      @PathVariable Long id) {
        attendanceService.createAttendance(selectedUsers, id);
        return "welcome";
    }
}
