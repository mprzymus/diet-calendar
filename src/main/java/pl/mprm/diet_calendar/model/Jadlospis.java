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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@ManyToOne
	private Kalendarz kalendarz;

	@OneToMany
	private Collection<Posilek> posilki = new HashSet<>();
	private Date data;
	private Double deficytKaloryczny;

}