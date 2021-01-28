package pl.mprm.diet_calendar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mprm.diet_calendar.dao.DailyMenuRepository;
import pl.mprm.diet_calendar.model.DailyMenu;
import pl.mprm.diet_calendar.model.Posilek;

import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
class DailyMenuServiceIT {

    @Autowired
    DailyMenuService dailyMenuService;

    @Autowired
    DailyMenuRepository repository;

    private final Integer hour = 1;
    private final Integer minute = 15;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void saveNewMeal() {
        var menu = new DailyMenu();
        var meal = new Posilek();
        meal.setGodzinaPosilku(hour);
        meal.setMinutaPosilku(minute);

        dailyMenuService.saveMeal(menu, meal);

        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        var saved_menu = result.get(0);
        assertEquals(1, saved_menu.getPosilki().size());
    }

    @Test
    void saveMeal() {
        var menu = new DailyMenu();
        var meal = new Posilek();
        meal.setGodzinaPosilku(hour);
        meal.setMinutaPosilku(minute);
        dailyMenuService.save(menu);

        dailyMenuService.saveMeal(menu, meal);

        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        var saved_menu = result.get(0);
        assertEquals(1, saved_menu.getPosilki().size());
    }
}