package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produkt {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToMany
	private Collection<Makroskladnik> makroskladniki;

	private Double kalorycznosc;
	private String nazwa;
	private Double gramatura;
	private String opis;

	@Column(name = "czy_pelnowartosciowy")
	private Boolean czyPelnowartosciowy;

}