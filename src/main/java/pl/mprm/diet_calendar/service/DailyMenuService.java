package pl.mprm.diet_calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprm.diet_calendar.dao.DailyMenuRepository;
import pl.mprm.diet_calendar.model.DailyMenu;
import pl.mprm.diet_calendar.model.Calendar;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DailyMenuService {
    private final DailyMenuRepository dailyMenuRepository;

    public DailyMenu findByDate(LocalDate date, Calendar calendar) {
        //return dailyMenuRepository.findByCalendarAndDate(calendar, date).orElse(new DailyMenu());
        return null;
    }
}
