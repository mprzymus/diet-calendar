package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Kalendarz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Pacjent pacjent;
	@OneToMany
	private Collection<Jadlospis> jadlospisy = new HashSet<>();
	private String nazwaKalendarza;

	@Column(name = "czy_aktywne")
	private Boolean czyAktywne;

}