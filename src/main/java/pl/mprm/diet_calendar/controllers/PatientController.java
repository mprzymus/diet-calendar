package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mprm.diet_calendar.model.Posilek;
import pl.mprm.diet_calendar.service.DailyMenuService;
import pl.mprm.diet_calendar.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
@RequestMapping("/patient")
public class PatientController {
    private final DailyMenuService dailyMenuService;
    private final UserService userService;


    @GetMapping("/calendar/{year}/{month}")
    public String showCalendar(Model model, @PathVariable Integer year, @PathVariable Integer month) {
        var userName = userService.getUsername();
        model.addAttribute("userName", userName);
        model.addAttribute("menu", dailyMenuService.findMenusForMonth(year,month, userName));
        return "patient/calendar";
    }

    @PostMapping("/calendar/{year}/{month}/edit")
    public String editMeal(@PathVariable Integer year, @PathVariable Integer month,
                           @ModelAttribute("meal") @Valid Posilek meal) {
        int day = 0; //TODO HOW THEY WOULD BE PASSED?
        var menu = dailyMenuService.findByDate(LocalDate.of(year, month, day), userService.getUsername());
        var toUpdateOptional = menu.getPosilki().stream()
                .filter(saved -> saved.getId().equals(meal.getId())).findAny();
        toUpdateOptional.ifPresent(element -> menu.getPosilki().remove(element));
        meal.setDailyMenu(menu);
        menu.getPosilki().add(meal);
        dailyMenuService.save(menu);
        return "redirect:/patient/calendar" + year + "/" + month;
    }

    @GetMapping
    public String welcomePage(Model model) {
        var userName = userService.getUsername();
        var dailyMenu = dailyMenuService.findByDate(LocalDate.now(), userName);
        model.addAttribute("menu", dailyMenu);
        return "patient/welcome";
    }
}
