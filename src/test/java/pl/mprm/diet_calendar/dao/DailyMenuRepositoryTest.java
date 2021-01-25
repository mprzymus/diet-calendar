package pl.mprm.diet_calendar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mprm.diet_calendar.model.Calendar;
import pl.mprm.diet_calendar.model.DailyMenu;
import pl.mprm.diet_calendar.model.Pacjent;

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
        Pacjent user = new Pacjent();
        user.setLogin(LOGIN);
        user.setCalendar(calendar);
        calendar.setPacjent(user);
        calendar.getJadlospisy().add(dailyMenu);
        dailyMenu.setCalendar(calendar);
        dailyMenu.setDate(DATE);

        dailyMenuRepository.save(dailyMenu);
    }
    @Test
    void findByCalendarPacjentLoginAndAndDate() {
        var result = dailyMenuRepository.findByCalendarPacjentLoginAndDate(LOGIN, DATE);
        assertTrue(result.isPresent());
        var ob = result.get();
        assertEquals(DATE, ob.getDate());
        assertEquals(LOGIN, ob.getCalendar().getPacjent().getLogin());
        System.out.println(ob);
    }
}