package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Calendar calendar;

	@ManyToOne(targetEntity = Dietitian.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "dietitian_id")
	private Dietitian dietitian;

	@Enumerated(value = EnumType.ORDINAL)
	private PhysicalActivity physicalActivity;

	private Double weight;
	private Double height;
	private Double caloricDemand;
	private Boolean isPremium;

	@Enumerated(value = EnumType.STRING)
	private Sex sex;

	private LocalDate dataUrodzenia;

	private String login;

}