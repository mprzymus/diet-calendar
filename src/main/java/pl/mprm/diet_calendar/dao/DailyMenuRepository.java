package pl.mprm.diet_calendar.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprm.diet_calendar.model.DailyMenu;
import pl.mprm.diet_calendar.model.Calendar;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyMenuRepository extends CrudRepository<DailyMenu, Long> {
    Optional<DailyMenu> findByCalendarAndDate(Calendar calendar, LocalDate date);
    Optional<DailyMenu> findByCalendarPacjentLoginAndDate(String calendarPacjentLogin, LocalDate date);
}