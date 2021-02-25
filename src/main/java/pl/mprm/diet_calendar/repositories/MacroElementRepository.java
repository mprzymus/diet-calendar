package pl.mprm.diet_calendar.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprm.diet_calendar.model.product_data.MicroElement;
import pl.mprm.diet_calendar.model.product_data.Product;

@Repository
public interface MacroElementRepository  extends CrudRepository<MicroElement, Long> {
    void deleteAllByProduct(Product product);
}
