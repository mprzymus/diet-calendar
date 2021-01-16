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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produkt")
	private Collection<Makroskladnik> makroskladniki = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produkt")
	private Collection<Mikroskladnik> mikroskladniki = new HashSet<>();

	private Double kalorycznosc;
	private String nazwa;
	private Double gramatura;
	private String opis;

	@Column(name = "czy_pelnowartosciowy")
	private Boolean czy_pelnowartosciowy;

}