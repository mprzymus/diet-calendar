package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Jadlospis")
public class DailyMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Calendar.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "kalendarzid")
	private Calendar calendar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dailyMenu", fetch = FetchType.EAGER)
	private Collection<Posilek> posilki = new HashSet<>();

	@Column(name = "data")
	private LocalDate date;

	@Column(name = "deficytKaloryczny")
	private Double deficytKaloryczny;

}