package pl.mprm.diet_calendar.controllers.product;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.Collection;

@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {

    @Synchronized
    @Override
    public ProductCommand convert(Product source) {
        var command = new ProductCommand();
        command.setId(source.getId());
        command.setGramatura(source.getGramatura());
        command.setNazwa(source.getName());
        command.setOpis(source.getOpis());
        command.setCzyPelnowartosciowy(source.getCzyPelnowartosciowy());
        command.setKalorycznosc(source.getKalorycznosc());
        if (source.getMakroskladniki() != null) {
            command.setMakroskladniki(mapToString(source.getMakroskladniki()));
        } else {
            command.setMakroskladniki("");
        }
        if (source.getMikroskladniki() != null) {
            command.setMikroskladniki(mapToString(source.getMikroskladniki()));
        } else {
            command.setMikroskladniki("");
        }
        return command;
    }

    private <T> String mapToString(Collection<T> collection) {
        return collection.stream().map(ob -> ob.toString() + "\n")
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append).toString();
    }
}
