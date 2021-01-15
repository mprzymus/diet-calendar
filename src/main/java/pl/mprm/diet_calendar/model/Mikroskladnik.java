package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mikroskladnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Produkt.class)
	@JoinColumn(name = "produktid")
	private Produkt produkt;

	private String nazwa;
	private Double ilosc;
}