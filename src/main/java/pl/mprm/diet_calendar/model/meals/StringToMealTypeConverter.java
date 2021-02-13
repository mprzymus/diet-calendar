package pl.mprm.diet_calendar.model.meals;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import pl.mprm.diet_calendar.configurations.MealTypesNameConfig;

import java.util.HashMap;
import java.util.Map;

public class StringToMealTypeConverter implements Converter<String, MealType> {

    private final Map<String, MealType> meals = new HashMap<>();

    public StringToMealTypeConverter(MealTypesNameConfig mealTypesNameConfig) {
        var names = mealTypesNameConfig.getMealNames();
        meals.put(names.get(0), MealType.BREAKFAST);
        meals.put(names.get(1), MealType.LUNCH);
        meals.put(names.get(2), MealType.DINNER);
        meals.put(names.get(3), MealType.TEA);
        meals.put(names.get(4), MealType.DESSERT);
        meals.put(names.get(5), MealType.SNACK);
    }

    @Override
    @Nullable
    public MealType convert(String source) {
        return meals.get(source);
    }
}
