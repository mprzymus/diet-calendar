package pl.mprm.diet_calendar.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.mprm.diet_calendar.model.users.Dietitian;
import pl.mprm.diet_calendar.model.users.Patient;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DietitianRepository dietitianRepository;

    @Autowired
    private PatientRepository patientRepository;

    private final String PATIENT_LOGIN = "Patient";
    private final String DIETITIAN_LOGIN = "Dietitian";

    @BeforeEach
    void setUp() {
        var dietitian = new Dietitian();
        dietitian.setLogin(DIETITIAN_LOGIN);
        dietitianRepository.save(dietitian);

        var patient = new Patient();
        patient.setLogin(PATIENT_LOGIN);
        patientRepository.save(patient);
    }

    @Test
    void findPatientByLogin() {
        var patientOptional = userRepository.findByLogin(PATIENT_LOGIN);

        assertTrue(patientOptional.isPresent());
        var patient = patientOptional.get();

        assertEquals(PATIENT_LOGIN, patient.getLogin());
    }

    @Test
    void findDietitianByLogin() {
        var dietOptional = userRepository.findByLogin(DIETITIAN_LOGIN);

        assertTrue(dietOptional.isPresent());
        var dietitian = dietOptional.get();

        assertEquals(DIETITIAN_LOGIN, dietitian.getLogin());
    }
}