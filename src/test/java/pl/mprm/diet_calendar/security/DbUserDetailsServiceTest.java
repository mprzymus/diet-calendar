package pl.mprm.diet_calendar.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.mprm.diet_calendar.model.users.Dietitian;
import pl.mprm.diet_calendar.model.users.Patient;
import pl.mprm.diet_calendar.model.users.User;
import pl.mprm.diet_calendar.repositories.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DbUserDetailsServiceTest {

    private final String LOGIN = "login";
    private final String PASSWORD = "pass";

    @InjectMocks
    private DbUserDetailsService service;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadPatientByUsernameTest() {
        var user = new Patient();
        setUserData(user);

        given(userRepository.findByLogin(anyString())).willReturn(Optional.of(user));

        //when
        UserDetails result = service.loadUserByUsername(LOGIN);

        assertDetailsFitUser(user, result);
    }

    @Test
    void loadDietitianByUsernameTest() {
        var user = new Dietitian();
        setUserData(user);

        given(userRepository.findByLogin(anyString())).willReturn(Optional.of(user));

        //when
        UserDetails result = service.loadUserByUsername(LOGIN);

        assertDetailsFitUser(user, result);
    }

    private void assertDetailsFitUser(User user, UserDetails result) {
        assertEquals(LOGIN, result.getUsername());
        assertEquals(PASSWORD, result.getPassword());
        assertIterableEquals(user.getRoles(),
                result.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        then(userRepository).should().findByLogin(anyString());
    }

    private void setUserData(User user) {
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
    }
}