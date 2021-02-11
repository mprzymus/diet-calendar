package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dietitian {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dietitian", targetEntity = Patient.class)
	private Collection<Patient> patients = new HashSet<>();

	private String login;
	private String password;
	private String email;

}