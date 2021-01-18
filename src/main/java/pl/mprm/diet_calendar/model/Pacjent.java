package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pacjent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Kalendarz kalendarz;

	@ManyToOne(targetEntity = Dietetyk.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "dietetykid")
	private Dietetyk dietetyk;

	@Enumerated(value = EnumType.ORDINAL)
	private WysilekFizyczny poziomAktywnosci;

	private Double waga;
	private Double wzrost;
	private Double zapotrzebowanieKaloryczne;
	private Boolean uzytkownikPremium;

	@Enumerated(value = EnumType.STRING)
	private Plec plec;

	private LocalDate dataUrodzenia;

}