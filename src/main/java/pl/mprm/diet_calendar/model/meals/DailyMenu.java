package pl.mprm.diet_calendar.model.meals;

import lombok.*;
import pl.mprm.diet_calendar.model.Calendar;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class DailyMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = pl.mprm.diet_calendar.model.Calendar.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "calendar_id")
	private Calendar calendar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dailyMenu", fetch = FetchType.EAGER)
	private Set<Meal> meals = new HashSet<>();

	@Column
	private LocalDate date;
}