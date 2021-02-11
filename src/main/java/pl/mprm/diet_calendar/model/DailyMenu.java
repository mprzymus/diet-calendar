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
@Table
public class DailyMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Calendar.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "calendar_id")
	private Calendar calendar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dailyMenu", fetch = FetchType.EAGER)
	private Set<Meal> meals = new HashSet<>();

	@Column
	private LocalDate date;
}