package pl.mprm.diet_calendar.model.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static pl.mprm.diet_calendar.configurations.SecurityConfigurationConst.DIETITIAN_USER_ROLE;
import static pl.mprm.diet_calendar.configurations.SecurityConfigurationConst.PATIENT_USER_ROLE;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dietitian extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dietitian", targetEntity = Patient.class)
    private Collection<Patient> patients = new HashSet<>();

    public Dietitian(Long id, String login, String password, Collection<Patient> patients, String email) {
        super(id, login, password, email);
        this.patients = patients;
    }

    @Override
    protected Collection<String> getRolesNames() {
        return Collections.singletonList(DIETITIAN_USER_ROLE);
    }
}