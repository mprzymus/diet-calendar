package pl.mprm.diet_calendar.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@Service
public class UserService {

    public static final String USER_NAME = "userName";
    public static final String ROLE = "role";

    public Model addUserToModel(Model model) {
        String principalName = getUsername();
        log.debug("User name: {}", principalName);
        model.addAttribute(USER_NAME, principalName);
        return model;
    }

    public Model addRoleToModel(Model model) {
        model.addAttribute(ROLE, getRole());
        return model;
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }
}
