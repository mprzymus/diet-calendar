package pl.mprm.diet_calendar.controllers.product;

import org.junit.jupiter.api.Test;
import pl.mprm.diet_calendar.model.product_data.Makroskladnik;
import pl.mprm.diet_calendar.model.product_data.Mikroskladnik;

import static org.junit.jupiter.api.Assertions.*;

class ProductCommandToProductTest {
    private final ProductCommandToProduct tested = new ProductCommandToProduct();

    @Test
    void nullElementsTest() {
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setMakroskladniki(null);
        product.setMikroskladniki(null);

        var result = tested.convert(product);

        assertTrue(result.getMikroskladniki().isEmpty());
        assertTrue(result.getMakroskladniki().isEmpty());
        assertEquals(1, result.getId());
    }

    @Test
    void noElementsTest() {
        ProductDto product = new ProductDto();
        product.setId(1L);

        var result = tested.convert(product);

        assertTrue(result.getMikroskladniki().isEmpty());
        assertTrue(result.getMakroskladniki().isEmpty());
        assertEquals(1, result.getId());
    }

    @Test
    void emptyElementsTest() {
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setMakroskladniki("");
        product.setMikroskladniki("");

        var result = tested.convert(product);

        assertTrue(result.getMikroskladniki().isEmpty());
        assertTrue(result.getMakroskladniki().isEmpty());
        assertEquals(1, result.getId());
    }

    @Test
    void elementsTest() {
        ProductDto product = new ProductDto();
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

        product.setMakroskladniki(macro.toString() + "\n");
        product.setMikroskladniki(micro1.toString() + "\n" + micro2.toString() + "\n");

        var result = tested.convert(product);

        assertEquals(1, result.getMakroskladniki().size());
        assertEquals(2, result.getMikroskladniki().size());
        assertTrue(
                result.getMakroskladniki().stream().map(Makroskladnik::getNazwa)
                        .anyMatch(name -> name.equals(macro.getNazwa()))
        );
        assertTrue(
                result.getMikroskladniki().stream().map(Mikroskladnik::getNazwa)
                        .anyMatch(name -> name.equals(micro1.getNazwa()))
        );
        assertTrue(
                result.getMikroskladniki().stream().map(Mikroskladnik::getNazwa)
                        .anyMatch(name -> name.equals(micro2.getNazwa()))
        );
        assertTrue(
                result.getMakroskladniki().stream().map(Makroskladnik::getIlosc)
                        .anyMatch(name -> name.equals(macro.getIlosc()))
        );
        assertTrue(
                result.getMikroskladniki().stream().map(Mikroskladnik::getIlosc)
                        .anyMatch(name -> name.equals(micro1.getIlosc()))
        );
        assertTrue(
                result.getMikroskladniki().stream().map(Mikroskladnik::getIlosc)
                        .anyMatch(name -> name.equals(micro2.getIlosc()))
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