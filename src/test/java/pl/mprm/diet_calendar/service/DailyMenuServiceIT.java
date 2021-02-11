package pl.mprm.diet_calendar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mprm.diet_calendar.dao.DailyMenuRepository;
import pl.mprm.diet_calendar.model.DailyMenu;
import pl.mprm.diet_calendar.model.Meal;

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

    private final Integer POSITIVE_HOUR = 1;
    private final Integer NEGATIVE_HOUR = -1;
    private final Integer ZERO_HOUR = 0;
    private final Integer MAX_HOUR = 23;
    private final Integer HIGH_HOUR = 1234;


    private final Integer POSITIVE_MINUTE = 1;
    private final Integer NEGATIVE_MINUTE = -1;
    private final Integer ZERO_MINUTE = 0;
    private final Integer MAX_MINUTE = 59;
    private final Integer HIGH_MINUTE = 1234;


    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void saveNewMeal() {
        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(POSITIVE_HOUR);
        meal.setMinute(POSITIVE_MINUTE);

        dailyMenuService.saveMeal(menu, meal);

        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        var saved_menu = result.get(0);
        assertEquals(1, saved_menu.getMeals().size());
    }

    @Test
    void saveMeal() {
        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(POSITIVE_HOUR);
        meal.setMinute(POSITIVE_MINUTE);
        dailyMenuService.save(menu);

        dailyMenuService.saveMeal(menu, meal);

        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        var saved_menu = result.get(0);
        assertEquals(1, saved_menu.getMeals().size());
    }
    @Test
    void saveNewMealMaxHour() {
        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(MAX_HOUR);
        meal.setMinute(POSITIVE_MINUTE);

        dailyMenuService.saveMeal(menu, meal);

        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        var saved_menu = result.get(0);
        assertEquals(1, saved_menu.getMeals().size());
    }

    @Test
    void saveNewMealMaxMinute() {
        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(POSITIVE_HOUR);
        meal.setMinute(MAX_MINUTE);
        dailyMenuService.save(menu);

        dailyMenuService.saveMeal(menu, meal);

        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        var saved_menu = result.get(0);
        assertEquals(1, saved_menu.getMeals().size());
    }
    @Test
    void saveNewMealZeroMinute() {
        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(POSITIVE_HOUR);
        meal.setMinute(ZERO_MINUTE);
        dailyMenuService.save(menu);

        dailyMenuService.saveMeal(menu, meal);

        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        var saved_menu = result.get(0);
        assertEquals(1, saved_menu.getMeals().size());
    }
    @Test
    void saveMealNegativeHour() {

        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(NEGATIVE_HOUR);
        meal.setMinute(POSITIVE_MINUTE);
        dailyMenuService.save(menu);
        Exception exception = assertThrows(
                javax.validation.ConstraintViolationException.class,
                () -> {
                    dailyMenuService.saveMeal(menu, meal);
                }
        );
        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        var saved_menu = result.get(0);
        String expected = "Validation failed";
        String msg = exception.getMessage();

        assertTrue(msg.contains(expected));
    }
    @Test
    void saveMealZeroHour() {
        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(ZERO_HOUR);
        meal.setMinute(POSITIVE_MINUTE);
        dailyMenuService.save(menu);

        dailyMenuService.saveMeal(menu, meal);

        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        var saved_menu = result.get(0);
        assertEquals(1, saved_menu.getMeals().size());
    }
    @Test
    void saveMealHighHour() {

        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(HIGH_HOUR);
        meal.setMinute(POSITIVE_MINUTE);
        dailyMenuService.save(menu);
        Exception exception = assertThrows(
                javax.validation.ConstraintViolationException.class,
                () -> {
                    dailyMenuService.saveMeal(menu, meal);
                }
        );
        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        var saved_menu = result.get(0);
        String expected = "Validation failed";
        String msg = exception.getMessage();

        assertTrue(msg.contains(expected));
    }
    @Test
    void saveMealLowMinute() {

        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(POSITIVE_HOUR);
        meal.setMinute(NEGATIVE_MINUTE);
        dailyMenuService.save(menu);
        Exception exception = assertThrows(
                javax.validation.ConstraintViolationException.class,
                () -> {
                    dailyMenuService.saveMeal(menu, meal);
                }
        );
        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        var saved_menu = result.get(0);
        String expected = "Validation failed";
        String msg = exception.getMessage();

        assertTrue(msg.contains(expected));
    }
    @Test
    void saveMealHighMinute() {

        var menu = new DailyMenu();
        var meal = new Meal();
        meal.setHour(POSITIVE_HOUR);
        meal.setMinute(HIGH_MINUTE);
        dailyMenuService.save(menu);
        Exception exception = assertThrows(
                javax.validation.ConstraintViolationException.class,
                () -> {
                    dailyMenuService.saveMeal(menu, meal);
                }
        );
        var result = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        var saved_menu = result.get(0);
        String expected = "Validation failed";
        String msg = exception.getMessage();

        assertTrue(msg.contains(expected));
    }
}