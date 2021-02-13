package pl.mprm.diet_calendar.model.meals;

import javax.persistence.Transient;

public enum MealType {
	BREAKFAST,
	LUNCH,
	DINNER,
	TEA,
	DESSERT,
	SNACK;

	@Transient
	public static MealTypeToStringConverter converter;

	@Override
	public String toString() {
		if (converter != null)
			return converter.convert(this);
		else
			return super.toString();
	}
}