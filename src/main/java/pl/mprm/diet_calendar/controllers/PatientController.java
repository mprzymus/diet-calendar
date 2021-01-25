package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mprm.diet_calendar.model.Posilek;
import pl.mprm.diet_calendar.service.DailyMenuService;
import pl.mprm.diet_calendar.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/patient")
public class PatientController {
    private final DailyMenuService dailyMenuService;
    private final UserService userService;
    private final UserController userController;

    @GetMapping("/calendar/{year}/{month}")
    public String showCalendar(Model model, @PathVariable Integer year, @PathVariable Integer month) {
        userController.addUserDataToModel(model);
        return "patient/calendar";
    }

    @GetMapping("/calendar/{year}/{month}/{day}")
    public String showDayData(Model model, @PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        log.debug("Showing details of day: {}-{}-{}", day, month, year);
        var userName = userService.getUsername();
        userController.addUserDataToModel(model);
        var menu = dailyMenuService.findByDate(LocalDate.of(year, month, day), userName);
        var mealsAsList = new ArrayList<>(menu.getPosilki());
        Collections.sort(mealsAsList);
        model.addAttribute("meals", mealsAsList);
        return "patient/calendar";
    }

    @GetMapping("/calendar")
    public String showCalendar() {
        var today = LocalDate.now();
        return "redirect:/patient/calendar/" + today.getYear() + "/" + today.getMonthValue() + "/" + today.getDayOfMonth();
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
