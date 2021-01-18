package pl.mprm.diet_calendar.model.product_data;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Produkt")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private Collection<Makroskladnik> makroskladniki = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private Collection<Mikroskladnik> mikroskladniki = new HashSet<>();

	private Double kalorycznosc;

	@Column(name = "nazwa")
	private String name;
	private Double gramatura;
	private String opis;

	@Column(name = "czy_pelnowartosciowy")
	private Boolean czyPelnowartosciowy;

}