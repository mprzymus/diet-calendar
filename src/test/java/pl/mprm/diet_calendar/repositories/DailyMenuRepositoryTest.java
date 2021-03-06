package pl.mprm.diet_calendar.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mprm.diet_calendar.model.Calendar;
import pl.mprm.diet_calendar.model.meals.DailyMenu;
import pl.mprm.diet_calendar.model.users.Patient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("dev")
class DailyMenuRepositoryTest {

    private final LocalDate DATE = LocalDate.now();
    private static final String LOGIN = "login";
    @Autowired
    DailyMenuRepository dailyMenuRepository;

    @BeforeEach
    void setUp() {
        DailyMenu dailyMenu = new DailyMenu();
        Calendar calendar = new Calendar();
        Patient user = new Patient();
        user.setLogin(LOGIN);
        user.setCalendar(calendar);
        calendar.setPatient(user);
        calendar.getDailyMenus().add(dailyMenu);
        dailyMenu.setCalendar(calendar);
        dailyMenu.setDate(DATE);

        dailyMenuRepository.save(dailyMenu);
    }

    @Test
    void findByCalendarPacjentLoginAndAndDate() {
        var result = dailyMenuRepository.findByCalendarPatientLoginAndDate(LOGIN, DATE);
        assertTrue(result.isPresent());
        var ob = result.get();
        assertEquals(DATE, ob.getDate());
        assertEquals(LOGIN, ob.getCalendar().getPatient().getLogin());
        System.out.println(ob);
    }

    @Test
    void findByCalendarPacjentLoginAndAndDateInvalidData() {
        assertDoesNotThrow(
                () -> dailyMenuRepository.findByCalendarPatientLoginAndDate
                        (LOGIN + "someText", DATE.minusDays(2)));

        var result = dailyMenuRepository.findByCalendarPatientLoginAndDate
                (LOGIN + "someText", DATE.minusDays(2));

        assertTrue(result.isEmpty());

    }
}