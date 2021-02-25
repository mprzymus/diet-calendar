package pl.mprm.diet_calendar.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mprm.diet_calendar.model.users.Dietitian;
import pl.mprm.diet_calendar.model.users.User;
import pl.mprm.diet_calendar.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static pl.mprm.diet_calendar.configurations.SecurityConfigurationConst.DIETITIAN_USER_ROLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("User \"{}\" try to login", username);
        var user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("No username: " + username));
        var details = new CalendarUserDetails();
        details.setUsername(username);
        details.setPassword(user.getPassword());
        details.setAuthorities(
                user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
        log.debug(details.getAuthorities().toString());
        return details;
    }
}
