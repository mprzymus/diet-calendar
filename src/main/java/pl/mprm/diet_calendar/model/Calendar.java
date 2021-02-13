package pl.mprm.diet_calendar.model;

import lombok.*;
import pl.mprm.diet_calendar.model.meals.DailyMenu;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Patient patient;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "calendar")
	private Collection<DailyMenu> dailyMenus = new HashSet<>();

	@Column
	private String name;

	@Column
	private Boolean isActive;

}