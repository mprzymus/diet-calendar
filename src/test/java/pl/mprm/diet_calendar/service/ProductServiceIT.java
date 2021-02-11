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
import pl.mprm.diet_calendar.model.product_data.MacroElement;
import pl.mprm.diet_calendar.model.product_data.MicroElement;
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
        var microElement1 = new MicroElement();
        var microElement2 = new MicroElement();

        var macroElement1 = new MacroElement();
        var macroElement2 = new MacroElement();

        prod.setMacroElements(Set.of(macroElement1, macroElement2));
        prod.setMicroElements(Set.of(microElement1, microElement2));

        productRepository.save(prod);

        var allProd = productService.findAllProducts();
        var prodFromDb = allProd.get(0);

        assertEquals(1, allProd.size());
        assertEquals(2, prodFromDb.getMicroElements().size());
        assertEquals(2, prodFromDb.getMacroElements().size());
    }

    @Test
    void saveEmptyProductTest() {
        assertEquals(0, productService.findAllProducts().size());

        var prod = new Product();

        productRepository.save(prod);
        assertEquals(1, productService.findAllProducts().size());
    }
}
