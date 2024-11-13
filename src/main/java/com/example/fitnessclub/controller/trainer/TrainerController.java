package com.example.fitnessclub.controller.trainer;

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

    @GetMapping("attendance/create/{id}")
    public String createAttendanceForm(@PathVariable Long id, Model model){
        model.addAttribute("classDateId", id);
        model.addAttribute("members", userService.findMembers());
        return "trainer/attendance";
    }

    @GetMapping("shifts")
    public String trainerShifts(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userDetails == null) return "redirect:/login";
        User user = userService.findUserByEmail(userDetails.getUsername());
        model.addAttribute("shifts", shiftService.findClassDateByTrainerId(user.getId()));
        return "trainer/classes";
    }

    @PostMapping("attendance/create/{id}")
    public String submitSelectedUsers(@RequestParam List<Long> selectedUsers,
                                      @PathVariable Long id) {
        attendanceService.createAttendance(selectedUsers, id);
        return "redirect:/trainer/classes/";
    }

    @GetMapping("/classes")
    public String trainerClasses(Model model) {
        model.addAttribute("classes", classService.findClassDates());
        return "members/classList";
    }
}
