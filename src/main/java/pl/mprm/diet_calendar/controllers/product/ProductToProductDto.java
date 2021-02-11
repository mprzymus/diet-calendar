package pl.mprm.diet_calendar.controllers.product;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.Collection;

@Component
public class ProductToProductDto implements Converter<Product, ProductDto> {

    @Synchronized
    @Override
    public ProductDto convert(Product source) {
        var command = new ProductDto();
        command.setId(source.getId());
        command.setGrams(source.getGrams());
        command.setName(source.getName());
        command.setDescription(source.getDescription());
        command.setIsNutritious(source.getIsNutritious());
        command.setCalories(source.getCalories());
        if (source.getMacroElements() != null) {
            command.setMacroElements(mapToString(source.getMacroElements()));
        } else {
            command.setMacroElements("");
        }
        if (source.getMicroElements() != null) {
            command.setMicroElements(mapToString(source.getMicroElements()));
        } else {
            command.setMicroElements("");
        }
        return command;
    }

    private <T> String mapToString(Collection<T> collection) {
        return collection.stream().map(ob -> ob.toString() + "\n")
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append).toString();
    }
}
