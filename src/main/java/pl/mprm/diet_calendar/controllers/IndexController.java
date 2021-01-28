package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mprm.diet_calendar.service.DailyMenuService;
import pl.mprm.diet_calendar.service.UserService;

import java.time.LocalDate;

import static pl.mprm.diet_calendar.configurations.SecurityConfigurationConst.PATIENT_USER_ROLE;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserController userController;
    private final UserService userService;
    private final DailyMenuService dailyMenuService;

    @GetMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        userController.addUserDataToModel(model);
        if (userService.getRole().contains(PATIENT_USER_ROLE)) {
            log.debug("User in");
            model = userController.addUserDataToModel(model);
            var userName = userService.getUsername();
            var dailyMenu = dailyMenuService.findByDate(LocalDate.now(), userName);
            model.addAttribute("menu", dailyMenu.getPosilki());
        }
        return "index";
    }
}
