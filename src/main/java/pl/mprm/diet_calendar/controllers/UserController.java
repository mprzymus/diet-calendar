package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.mprm.diet_calendar.service.UserService;

@Service
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public Model addUserDataToModel(Model model) {
        model = userService.addUserToModel(model);
        model = userService.addRoleToModel(model);
        return model;
    }
}
