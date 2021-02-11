package pl.mprm.diet_calendar.controllers.product;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.mprm.diet_calendar.model.product_data.MacroElement;
import pl.mprm.diet_calendar.model.product_data.MicroElement;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductCommandToProduct implements Converter<ProductDto, Product> {

    @Synchronized
    @Override
    public Product convert(ProductDto source) {
        var product = new Product();
        product.setId(source.getId());
        product.setGrams(source.getGrams());
        product.setName(source.getName());
        product.setDescription(source.getDescription());
        product.setIsNutritious(source.getIsNutritious());
        product.setCalories(source.getCalories());
        if (source.getMacroElements() != null && !source.getMacroElements().isBlank()) {
            var macros = toMap(source.getMacroElements(), this::createMacroElement);
            macros.forEach(macro -> macro.setProduct(product));
            product.setMacroElements(macros);
        }
        if (source.getMicroElements() != null && !source.getMicroElements().isBlank()) {
            var micros = toMap(source.getMicroElements(), this::createMicroElement);
            micros.forEach(micro -> micro.setProduct(product));
            product.setMicroElements(micros);
        }
        return product;
    }

    private <T> Set<T> toMap(String elements, Function<String[], T> fun) {
        var splitted = elements.split("\n");
        return Arrays.stream(splitted).map(element -> element.split(": "))
                .map(fun)
                .collect(Collectors.toSet());
    }

    private MacroElement createMacroElement(String[] str) {
        checkSize(str);
        var toReturn = new MacroElement();
        toReturn.setAmount(Double.parseDouble(str[1]));
        toReturn.setName(str[0]);
        return toReturn;
    }
    private MicroElement createMicroElement(String[] str) {
        checkSize(str);
        var toReturn = new MicroElement();
        toReturn.setAmount(Double.parseDouble(str[1]));
        toReturn.setName(str[0]);
        return toReturn;
    }

    private void checkSize(String[] str) {
        if (str.length != 2) {
            throw new IllegalArgumentException("Parsed line illegal number of elements: " + str.length);
        }
    }
}
