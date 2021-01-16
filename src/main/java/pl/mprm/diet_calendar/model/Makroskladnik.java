package pl.mprm.diet_calendar.model;

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

}