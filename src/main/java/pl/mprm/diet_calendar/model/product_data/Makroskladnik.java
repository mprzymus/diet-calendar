package pl.mprm.diet_calendar.model.product_data;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Makroskladnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "produktid")
	private Product product;

	private String nazwa;
	private Double ilosc;

	@Override
	public String toString() {
		return ElementUtils.generateElementString(nazwa, ilosc);
	}
}