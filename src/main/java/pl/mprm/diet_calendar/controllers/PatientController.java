package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mprm.diet_calendar.dao.MealRepository;
import pl.mprm.diet_calendar.model.Meal;
import pl.mprm.diet_calendar.service.DailyMenuService;
import pl.mprm.diet_calendar.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/patient")
public class PatientController {
    private final DailyMenuService dailyMenuService;
    private final UserService userService;
    private final UserController userController;
    private final MealRepository mealRepository;

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
        var menu = dailyMenuService.findByDate(LocalDate.of(year, month, day), null);
        var mealsAsList = new ArrayList<>(menu.getMeals());
        Collections.sort(mealsAsList);
        model.addAttribute("meals", mealsAsList);
        addDateToModel(model, year, month, day);
        return "patient/calendar";
    }

    private void addDateToModel(Model model, Integer year, Integer month, Integer day) {
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
    }

    @GetMapping("/calendar")
    public String showCalendar() {
        var today = LocalDate.now();
        return "redirect:/patient/calendar/" + today.getYear() + "/" + today.getMonthValue() + "/" + today.getDayOfMonth();
    }

    @PostMapping("/calendar/{year}/{month}/{day}/edit")
    public String editMeal(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day,
                           @ModelAttribute("meal") @Valid Meal meal) {
        var menu = dailyMenuService.findByDate(LocalDate.of(year, month, day), null);
        if (meal.getId() != null) {
            var toUpdateOptional = menu.getMeals().stream()
                    .filter(saved -> saved.getId().equals(meal.getId())).findAny();
            toUpdateOptional.ifPresent(element -> menu.getMeals().remove(element));
        }
        //TODO TIGHT NEW MENU WITH CALENDAR
        //meal.getSkladniki().forEach(skl -> skl.setDanie(meal));
        meal.setDailyMenu(menu);
        menu.getMeals().add(meal);
        dailyMenuService.save(menu);
        return "redirect:/patient/calendar/" + year + "/" + month + "/" + day;
    }

    @GetMapping("/calendar/{year}/{month}/{day}/{id}/delete")
    public String deleteMeal(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day, @PathVariable Long id) {
        dailyMenuService.deleteMealById(LocalDate.of(year, month, day), null, id);
        return "redirect:/patient/calendar/" + year + "/" + month + "/" + day;
    }


    @PostMapping("/calendar/{year}/{month}/{day}/new")
    public String addMeal(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day,
                          @ModelAttribute("meal") @Valid Meal meal) {
        return editMeal(year, month, day, meal);
    }

    @GetMapping("/calendar/{year}/{month}/{day}/{id}/edit")
    public String showEditMeal(Model model, @PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day,
                               @PathVariable Long id) {
        addDateToModel(model, year, month, day);
        model = userController.addUserDataToModel(model);
        var meal = mealRepository.findById(id);
        if (meal.isEmpty()) {
            return "redirect:/patient/calendar" + year + "/" + month + "/" + day;
        }
        model.addAttribute("meal", meal.get());
        return "patient/addMeal";
    }

    @GetMapping("/calendar/{year}/{month}/{day}/new")
    public String showNewMeal(Model model, @PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        var meal = new Meal();
        addDateToModel(model, year, month, day);
        model = userController.addUserDataToModel(model);
        model.addAttribute("meal", meal);
        return "patient/addMeal";
    }

    @GetMapping
    public String welcomePage(Model model) {
        return "redirect:index";
    }
}
