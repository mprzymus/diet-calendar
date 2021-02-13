package pl.mprm.diet_calendar.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.mprm.diet_calendar.model.meals.MealType;
import pl.mprm.diet_calendar.model.meals.MealTypeToStringConverter;
import pl.mprm.diet_calendar.model.meals.StringToMealTypeConverter;

@RequiredArgsConstructor
@Configuration
public class MealTypeConfig implements WebMvcConfigurer {

    private final MealTypesNameConfig mealTypesNameConfig;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToMealTypeConverter(mealTypesNameConfig));
        MealType.converter = new MealTypeToStringConverter(mealTypesNameConfig);
    }
}