package pl.mprm.diet_calendar.controllers.product;

import org.junit.jupiter.api.Test;
import pl.mprm.diet_calendar.model.product_data.MacroElement;
import pl.mprm.diet_calendar.model.product_data.MicroElement;

import static org.junit.jupiter.api.Assertions.*;

class ProductCommandToProductTest {
    private final ProductCommandToProduct tested = new ProductCommandToProduct();

    @Test
    void nullElementsTest() {
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setMacroElements(null);
        product.setMicroElements(null);

        var result = tested.convert(product);

        assertTrue(result.getMicroElements().isEmpty());
        assertTrue(result.getMacroElements().isEmpty());
        assertEquals(1, result.getId());
    }

    @Test
    void noElementsTest() {
        ProductDto product = new ProductDto();
        product.setId(1L);

        var result = tested.convert(product);

        assertTrue(result.getMicroElements().isEmpty());
        assertTrue(result.getMacroElements().isEmpty());
        assertEquals(1, result.getId());
    }

    @Test
    void emptyElementsTest() {
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setMacroElements("");
        product.setMicroElements("");

        var result = tested.convert(product);

        assertTrue(result.getMicroElements().isEmpty());
        assertTrue(result.getMacroElements().isEmpty());
        assertEquals(1, result.getId());
    }

    @Test
    void elementsTest() {
        ProductDto product = new ProductDto();
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

        product.setMacroElements(macro.toString() + "\n");
        product.setMicroElements(micro1.toString() + "\n" + micro2.toString() + "\n");

        var result = tested.convert(product);

        assertEquals(1, result.getMacroElements().size());
        assertEquals(2, result.getMicroElements().size());
        assertTrue(
                result.getMacroElements().stream().map(MacroElement::getName)
                        .anyMatch(name -> name.equals(macro.getName()))
        );
        assertTrue(
                result.getMicroElements().stream().map(MicroElement::getName)
                        .anyMatch(name -> name.equals(micro1.getName()))
        );
        assertTrue(
                result.getMicroElements().stream().map(MicroElement::getName)
                        .anyMatch(name -> name.equals(micro2.getName()))
        );
        assertTrue(
                result.getMacroElements().stream().map(MacroElement::getAmount)
                        .anyMatch(name -> name.equals(macro.getAmount()))
        );
        assertTrue(
                result.getMicroElements().stream().map(MicroElement::getAmount)
                        .anyMatch(name -> name.equals(micro1.getAmount()))
        );
        assertTrue(
                result.getMicroElements().stream().map(MicroElement::getAmount)
                        .anyMatch(name -> name.equals(micro2.getAmount()))
        );
    }
    @Test
    void createMacroElementEmptyStringTest() {
        assertThrows(IllegalArgumentException.class, () -> tested.toMap("", tested::createMacroElement));
    }
    @Test
    void createMacroElementRandomStringTest() {
        assertThrows(IllegalArgumentException.class, () -> tested.toMap("dfchgjcvbnm", tested::createMacroElement));
    }
    @Test
    void createMacroElementCorrectStringTest() {
        assertThrows(IllegalArgumentException.class, () -> tested.toMap("Fat: 4g", tested::createMacroElement));
    }
    @Test
    void createMacroElementNoColonTest() {
        assertThrows(IllegalArgumentException.class, () -> tested.toMap("Fat 4g", tested::createMacroElement));
    }
    @Test
    void createMacroElementOnlyElementNameTest() {
        assertThrows(IllegalArgumentException.class, () -> tested.toMap("dfchgjcvbnm", tested::createMacroElement));
    }
    @Test
    void createMacroElementOnlyGrammageTest() {
        assertThrows(IllegalArgumentException.class, () -> tested.toMap("5g", tested::createMacroElement));
    }
    @Test
    void createMacroElementTwoElementsTest() {
        assertThrows(IllegalArgumentException.class, () -> tested.toMap("Fat: 4g, \n" +
                "Carbohydrates: 10g", tested::createMacroElement));
    }
}