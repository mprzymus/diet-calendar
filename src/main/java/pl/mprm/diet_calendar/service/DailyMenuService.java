package pl.mprm.diet_calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mprm.diet_calendar.dao.DailyMenuRepository;
import pl.mprm.diet_calendar.model.DailyMenu;
import pl.mprm.diet_calendar.model.Posilek;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DailyMenuService {
    private final DailyMenuRepository dailyMenuRepository;

    public DailyMenu findByDate(LocalDate date, String userName) {
        var menu = dailyMenuRepository.findByCalendarPacjentLoginAndDate(userName, date).orElse(new DailyMenu());
        menu.setDate(date);
        return menu;
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

    public void saveMeal(DailyMenu menu, Posilek meal) {
        meal.setDailyMenu(menu);
        menu.getPosilki().add(meal);
        save(menu);
    }

    public DailyMenu save(DailyMenu menu) {
        return dailyMenuRepository.save(menu);
    }

    @Transactional
    public void deleteMealById(LocalDate date, String username, Long mealId) {
        var menu = findByDate(date, username);
        var mealOptional = menu.getPosilki().stream()
                .filter(meal -> meal.getId().equals(mealId)).findAny();
        if (mealOptional.isPresent()) {
            var meal = mealOptional.get();
            meal.setDailyMenu(null);
            menu.getPosilki().remove(meal);
            save(menu);
        }
    }
}
