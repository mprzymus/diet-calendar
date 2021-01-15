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
public class Dietetyk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	private Collection<Pacjent> pacjenci = new HashSet<>();

	private String login;
	private String password;
	private String email;

}