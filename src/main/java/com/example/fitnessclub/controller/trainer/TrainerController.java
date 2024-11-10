package com.example.fitnessclub.controller.trainer;

import com.example.fitnessclub.Service.ClassService;
import com.example.fitnessclub.Service.ClassServiceImpl;
import com.example.fitnessclub.Service.ShiftService;
import com.example.fitnessclub.Service.ShiftServiceImpl;
import com.example.fitnessclub.model.Attendance;
import com.example.fitnessclub.model.Shift;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TrainerController {
    private final ClassService classService;
    private final ShiftService shiftService;

    public TrainerController(ClassServiceImpl classServiceImpl, ShiftServiceImpl shiftServiceImpl) {
        this.classService = classServiceImpl;
        this.shiftService = shiftServiceImpl;
    }

    @GetMapping("/trainer/attendance/create/{id}")
    public String createAttendanceForm(@PathVariable Long id, Model model){
        model.addAttribute("attendance", new Attendance());
        return "attendance";
    }

    @GetMapping("/trainer/shifts/{id}")
    public String trainerShifts(@PathVariable Long id, Model model){
        model.addAttribute("shifts", shiftService.findClassDateByTrainerId(id));
        return "trainerClasses";
    }
}
