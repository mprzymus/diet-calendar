package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Jadlospis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Kalendarz.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "kalendarzid")
	private Kalendarz kalendarz;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jadlospis")
	private Collection<Posilek> posilki = new HashSet<>();

	private Date data;
	private Double deficytKaloryczny;

}