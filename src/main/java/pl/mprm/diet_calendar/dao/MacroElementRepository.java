package pl.mprm.diet_calendar.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mprm.diet_calendar.model.product_data.Mikroskladnik;
import pl.mprm.diet_calendar.model.product_data.Product;

public interface MacroElementRepository  extends CrudRepository<Mikroskladnik, Long> {
    void deleteAllByProduct(Product product);
}
