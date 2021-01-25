package pl.mprm.diet_calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprm.diet_calendar.dao.DailyMenuRepository;
import pl.mprm.diet_calendar.model.DailyMenu;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DailyMenuService {
    private final DailyMenuRepository dailyMenuRepository;

    public DailyMenu findByDate(LocalDate date, String userName) {
        return dailyMenuRepository.findByCalendarPacjentLoginAndDate(userName, date).orElse(new DailyMenu());
    }

    public Map<Integer, DailyMenu> findMenusForMonth(Integer year, Integer month, String userName) {
        var map = new HashMap<Integer, DailyMenu>();
        var yearMonth = YearMonth.of(year, month);
        var daysInMonth = yearMonth.lengthOfMonth();
        for (int i = 1; i <= daysInMonth; i++) {
            var menu = findByDate(LocalDate.of(year, month, i), userName);
            map.put(i, menu);
        }
        return map;
    }

    public void save(DailyMenu menu) {
        dailyMenuRepository.save(menu);
    }
}
