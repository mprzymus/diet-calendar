package pl.mprm.diet_calendar.controllers.product;

import org.junit.jupiter.api.Test;
import pl.mprm.diet_calendar.model.product_data.MacroElement;
import pl.mprm.diet_calendar.model.product_data.MicroElement;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductToProductDtoTest {

    private final ProductToProductDto tested = new ProductToProductDto();

    @Test
    void nullElementsTest() {
        Product product = new Product();
        product.setId(1L);
        product.setMacroElements(null);
        product.setMicroElements(null);

        var result = tested.convert(product);

        assertEquals("", result.getMicroElements());
        assertEquals("", result.getMacroElements());
        assertEquals(1, result.getId());
    }

    @Test
    void noElementsTest() {
        Product product = new Product();
        product.setId(1L);

        var result = tested.convert(product);

        assertEquals("", result.getMicroElements());
        assertEquals("", result.getMacroElements());
        assertEquals(1, result.getId());
    }

    @Test
    void emptyElementsTest() {
        Product product = new Product();
        product.setId(1L);
        product.setMacroElements(Set.of(new MacroElement()));
        product.setMicroElements(Set.of(new MicroElement()));

        var result = tested.convert(product);

        assertTrue(result.getMicroElements().isBlank());
        assertTrue(result.getMacroElements().isBlank());
        assertEquals(1, result.getId());
    }

    @Test
    void elementsTest() {
        Product product = new Product();
        product.setId(1L);
        var micro1 = new MicroElement();
        micro1.setName("m1");
        micro1.setAmount(1.0);
        var micro2 = new MicroElement();
        micro2.setName("m2");
        micro2.setAmount(2.0);
        var macro = new MacroElement();
        macro.setName("ma");
        macro.setAmount(1.0);

        product.setMacroElements(Set.of(macro));
        product.setMicroElements(Set.of(micro1, micro2));

        var command = tested.convert(product);
        var micro1AsString = micro1.getName() + ": " + micro1.getAmount().toString() + "\n";
        var micro2AsString = micro2.getName() + ": " + micro2.getAmount().toString() + "\n";
        var macroAsString = macro.getName() + ": " + macro.getAmount().toString() + "\n";
        assertEquals(macroAsString, command.getMacroElements());
        assertTrue(command.getMicroElements().contains(micro1AsString));
        assertTrue(command.getMicroElements().contains(micro2AsString));
    }
}