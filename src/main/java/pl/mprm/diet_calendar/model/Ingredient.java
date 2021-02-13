package pl.mprm.diet_calendar.model;

import lombok.*;
import pl.mprm.diet_calendar.model.meals.Meal;
import pl.mprm.diet_calendar.model.product_data.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient implements Serializable {

	@Id
	@ManyToOne(targetEntity = Meal.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "meal_id")
	private Meal meal;

	@Id
	@ManyToOne
	@JoinColumn(name = "produkt_id")
	private Product product;
	private Double amount;
}