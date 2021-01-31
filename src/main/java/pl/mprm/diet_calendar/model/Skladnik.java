package pl.mprm.diet_calendar.model;

import lombok.*;
import pl.mprm.diet_calendar.model.product_data.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skladnik implements Serializable {

	@Id
	@ManyToOne(targetEntity = Posilek.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "posilekid")
	private Posilek danie;

	@Id
	@ManyToOne
	@JoinColumn(name = "produkt_id")
	private Product produkty;
	private Double ilosc;
}