package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CalculatorController {

    private final UserController userController;

    @GetMapping("/open/calc")
    public String dietCalculator(Model model) {
        userController.addUserDataToModel(model);
        return "calcform";
    }
}
