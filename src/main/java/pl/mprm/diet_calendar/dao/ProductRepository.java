package pl.mprm.diet_calendar.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprm.diet_calendar.model.product_data.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
