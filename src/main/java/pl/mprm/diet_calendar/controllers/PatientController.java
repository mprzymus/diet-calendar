package pl.mprm.diet_calendar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        model.addAttribute("userName", "someName");
        return "patient/calendar";
    }
}
