package pl.mprm.diet_calendar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculatorController {
    @GetMapping("/open/calc")
    public String dietCalculator() {
        return "calcform";
    }
}
