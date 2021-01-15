package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Posilek {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "jadlospisid")
	private Jadlospis jadlospis;

	@OneToMany
	private Collection<Skladnik> skladniki = new HashSet<>();

	@Enumerated(value = EnumType.ORDINAL)
	private RodzajPosilku rodzajPosilku;

	private Integer godzinaPosilku;
	private Integer minutaPosilku;
	private Double kalorycznosc;

}