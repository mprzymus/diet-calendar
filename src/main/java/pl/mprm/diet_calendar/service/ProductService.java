package pl.mprm.diet_calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprm.diet_calendar.dao.ProductRepository;
import pl.mprm.diet_calendar.model.Produkt;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Produkt> findAllProducts() {
        var list = new ArrayList<Produkt>();
        productRepository.findAll().forEach(list::add);
        return list;
    }
}
