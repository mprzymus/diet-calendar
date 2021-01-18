package pl.mprm.diet_calendar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import pl.mprm.diet_calendar.dao.ProductRepository;
import pl.mprm.diet_calendar.model.product_data.Makroskladnik;
import pl.mprm.diet_calendar.model.product_data.Mikroskladnik;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceIT {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Transactional
    @Test
    void saveWithProductWithElementsTest() {
        var prod = new Product();
        var microElement1 = new Mikroskladnik();
        var microElement2 = new Mikroskladnik();

        var macroElement1 = new Makroskladnik();
        var macroElement2 = new Makroskladnik();

        prod.setMakroskladniki(Set.of(macroElement1, macroElement2));
        prod.setMikroskladniki(Set.of(microElement1, microElement2));

        productRepository.save(prod);

        var allProd = productService.findAllProducts();
        var prodFromDb = allProd.get(0);

        assertEquals(1, allProd.size());
        assertEquals(2, prodFromDb.getMikroskladniki().size());
        assertEquals(2, prodFromDb.getMakroskladniki().size());
    }
}
