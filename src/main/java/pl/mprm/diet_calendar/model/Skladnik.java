package pl.mprm.diet_calendar.model;

import lombok.*;
import pl.mprm.diet_calendar.model.product_data.Product;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skladnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Posilek danie;

	@ManyToOne
	private Product produkty;
	private Double ilosc;
}