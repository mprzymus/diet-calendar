package pl.mprm.diet_calendar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mprm.diet_calendar.model.users.Dietitian;
import pl.mprm.diet_calendar.model.users.Patient;

import java.util.Optional;

@Repository
public interface DietitianRepository extends JpaRepository<Dietitian, Long> {
    Optional<Dietitian> findByLogin(String name);

}
