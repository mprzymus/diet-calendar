package pl.mprm.diet_calendar.controllers.product;

import org.junit.jupiter.api.Test;
import pl.mprm.diet_calendar.model.product_data.Makroskladnik;
import pl.mprm.diet_calendar.model.product_data.Mikroskladnik;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductToProductCommandTest {

    private final ProductToProductCommand tested = new ProductToProductCommand();

    @Test
    void nullElementsTest() {
        Product product = new Product();
        product.setId(1L);
        product.setMakroskladniki(null);
        product.setMikroskladniki(null);

        var result = tested.convert(product);

        assertEquals("", result.getMikroskladniki());
        assertEquals("", result.getMakroskladniki());
        assertEquals(1, result.getId());
    }

    @Test
    void noElementsTest() {
        Product product = new Product();
        product.setId(1L);

        var result = tested.convert(product);

        assertEquals("", result.getMikroskladniki());
        assertEquals("", result.getMakroskladniki());
        assertEquals(1, result.getId());
    }

    @Test
    void emptyElementsTest() {
        Product product = new Product();
        product.setId(1L);
        product.setMakroskladniki(Set.of(new Makroskladnik()));
        product.setMikroskladniki(Set.of(new Mikroskladnik()));

        var result = tested.convert(product);

        assertEquals("", result.getMikroskladniki());
        assertEquals("", result.getMakroskladniki());
        assertEquals(1, result.getId());
    }
}