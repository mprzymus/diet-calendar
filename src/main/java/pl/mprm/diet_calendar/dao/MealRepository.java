package pl.mprm.diet_calendar.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprm.diet_calendar.model.Posilek;

@Repository
public interface MealRepository extends CrudRepository<Posilek, Long> {
}
