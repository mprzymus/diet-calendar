package pl.mprm.diet_calendar.controllers.product;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.mprm.diet_calendar.model.product_data.Makroskladnik;
import pl.mprm.diet_calendar.model.product_data.Mikroskladnik;
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
        product.setGramatura(source.getGramatura());
        product.setName(source.getNazwa());
        product.setOpis(source.getOpis());
        product.setCzyPelnowartosciowy(source.getCzyPelnowartosciowy());
        product.setKalorycznosc(source.getKalorycznosc());
        if (source.getMakroskladniki() != null && !source.getMakroskladniki().isBlank()) {
            var macros = toMap(source.getMakroskladniki(), this::createMacroElement);
            macros.forEach(macro -> macro.setProduct(product));
            product.setMakroskladniki(macros);
        }
        if (source.getMikroskladniki() != null && !source.getMikroskladniki().isBlank()) {
            var micros = toMap(source.getMikroskladniki(), this::createMicroElement);
            micros.forEach(micro -> micro.setProduct(product));
            product.setMikroskladniki(micros);
        }
        return product;
    }

    public <T> Set<T> toMap(String elements, Function<String[], T> fun) {
        var splitted = elements.split("\n");
        return Arrays.stream(splitted).map(element -> element.split(": "))
                .map(fun)
                .collect(Collectors.toSet());
    }

    public Makroskladnik createMacroElement(String[] str) {
        checkSize(str);
        var toReturn = new Makroskladnik();
        toReturn.setIlosc(Double.parseDouble(str[1]));
        toReturn.setNazwa(str[0]);
        return toReturn;
    }
    public Mikroskladnik createMicroElement(String[] str) {
        checkSize(str);
        var toReturn = new Mikroskladnik();
        toReturn.setIlosc(Double.parseDouble(str[1]));
        toReturn.setNazwa(str[0]);
        return toReturn;
    }

    private void checkSize(String[] str) {
        if (str.length != 2) {
            throw new IllegalArgumentException("Parsed line illegal number of elements: " + str.length);
        }
    }
}
