package pl.mprm.diet_calendar.model.meals;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import pl.mprm.diet_calendar.configurations.MealTypesNameConfig;

import java.util.HashMap;
import java.util.Map;

public class MealTypeToStringConverter implements Converter<MealType, String> {
    private final Map<MealType, String> meals = new HashMap<>();

    public MealTypeToStringConverter(MealTypesNameConfig mealTypesNameConfig) {
        var names = mealTypesNameConfig.getMealNames();
        meals.put(MealType.BREAKFAST, names.get(0));
        meals.put(MealType.LUNCH, names.get(1));
        meals.put(MealType.DINNER, names.get(2));
        meals.put(MealType.TEA, names.get(3));
        meals.put(MealType.DESSERT, names.get(4));
        meals.put(MealType.SNACK, names.get(5));
    }

    @Override
    @Nullable
    public String convert(MealType source) {
        return meals.get(source);
    }
}
