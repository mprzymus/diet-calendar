package pl.mprm.diet_calendar.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@Service
public class UserService {

    public static final String ATTRIBUTE_NAME = "userName";

    public Model addUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        log.debug("User name: {}", principalName);
        model.addAttribute(ATTRIBUTE_NAME, principalName);
        return model;
    }
}
