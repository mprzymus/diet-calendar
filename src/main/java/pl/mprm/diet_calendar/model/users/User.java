package pl.mprm.diet_calendar.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String login;
    private String password;
    private String email;

    public Collection<String> getRoles() {
        return getRolesNames().stream()
                .map(role -> "ROLE_" + role)
                .collect(Collectors.toList());
    }
    protected abstract Collection<String> getRolesNames();
}
