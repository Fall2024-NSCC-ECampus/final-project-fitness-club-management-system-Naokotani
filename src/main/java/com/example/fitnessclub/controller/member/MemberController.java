package com.example.fitnessclub.controller.member;

import com.example.fitnessclub.service.ClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member/")
@Controller
public class MemberController {
    private final ClassService classService;
    public MemberController(ClassService classService) {
        this.classService = classService;
    }



    @GetMapping("classes")
    public String memberClasses(Model model) {
        model.addAttribute("classes", classService.findClassDates());
        return "members/classList";
    }

}
