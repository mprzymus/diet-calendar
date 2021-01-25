package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Kalendarz")
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Pacjent pacjent;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "calendar")
	private Collection<DailyMenu> jadlospisy = new HashSet<>();
	private String nazwaKalendarza;

	@Column(name = "czy_aktywne")
	private Boolean czyAktywne;

}