package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mprm.diet_calendar.service.DailyMenuService;
import pl.mprm.diet_calendar.service.UserService;

import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
@RequestMapping("/patient")
public class PatientController {
    private final DailyMenuService dailyMenuService;
    private final UserService userService;


    @GetMapping("/calendar")
    public String showCalendar(Model model) {
        model.addAttribute("userName", "someName");
        return "patient/calendar";
    }

    @GetMapping
    public String welcomePage(Model model) {
        var userName = userService.getUsername();
        var dailyMenu = dailyMenuService.findByDate(LocalDate.now(), userName);
        model.addAttribute("menu", dailyMenu);
        return "patient/welcome";
    }
}
