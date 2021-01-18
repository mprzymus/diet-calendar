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

        assertTrue(result.getMikroskladniki().isBlank());
        assertTrue(result.getMakroskladniki().isBlank());
        assertEquals(1, result.getId());
    }

    @Test
    void elementsTest() {
        Product product = new Product();
        product.setId(1L);
        var micro1 = new Mikroskladnik();
        micro1.setNazwa("m1");
        micro1.setIlosc(1.0);
        var micro2 = new Mikroskladnik();
        micro2.setNazwa("m2");
        micro2.setIlosc(2.0);
        var macro = new Makroskladnik();
        macro.setNazwa("ma");
        macro.setIlosc(1.0);

        product.setMakroskladniki(Set.of(macro));
        product.setMikroskladniki(Set.of(micro1, micro2));

        var command = tested.convert(product);
        var micro1AsString = micro1.getNazwa() + ": " + micro1.getIlosc().toString() + "\n";
        var micro2AsString = micro2.getNazwa() + ": " + micro2.getIlosc().toString() + "\n";
        var macroAsString = macro.getNazwa() + ": " + macro.getIlosc().toString() + "\n";
        assertEquals(macroAsString, command.getMakroskladniki());
        assertTrue(command.getMikroskladniki().contains(micro1AsString));
        assertTrue(command.getMikroskladniki().contains(micro2AsString));
    }
}