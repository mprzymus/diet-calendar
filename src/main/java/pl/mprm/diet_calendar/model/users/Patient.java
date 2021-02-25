package pl.mprm.diet_calendar.model.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mprm.diet_calendar.model.Calendar;
import pl.mprm.diet_calendar.model.PhysicalActivity;
import pl.mprm.diet_calendar.model.Sex;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import static pl.mprm.diet_calendar.configurations.SecurityConfigurationConst.DIETITIAN_USER_ROLE;
import static pl.mprm.diet_calendar.configurations.SecurityConfigurationConst.PATIENT_USER_ROLE;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Patient extends User {

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

    private LocalDate birthDate;

    public Patient(Long id, String login, String password, String email, Calendar calendar, Dietitian dietitian, PhysicalActivity physicalActivity, Double weight, Double height, Double caloricDemand, Boolean isPremium, Sex sex, LocalDate birthDate) {
        super(id, login, password, email);
        this.calendar = calendar;
        this.dietitian = dietitian;
        this.physicalActivity = physicalActivity;
        this.weight = weight;
        this.height = height;
        this.caloricDemand = caloricDemand;
        this.isPremium = isPremium;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    @Override
    protected Collection<String> getRolesNames() {
        return Collections.singletonList(PATIENT_USER_ROLE);
    }
}