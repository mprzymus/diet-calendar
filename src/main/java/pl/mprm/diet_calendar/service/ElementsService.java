package pl.mprm.diet_calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mprm.diet_calendar.dao.MacroElementRepository;
import pl.mprm.diet_calendar.dao.MicroElementRepository;
import pl.mprm.diet_calendar.dao.ProductRepository;
import pl.mprm.diet_calendar.model.product_data.Product;

@Service
@RequiredArgsConstructor
public class ElementsService {

    private final MicroElementRepository microElementRepository;
    private final MacroElementRepository macroElementRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void deleteProductsElements(Product product) {
        if (product.getId() != null && productRepository.findById(product.getId()).isEmpty()) {
            microElementRepository.deleteAllByProduct(product);
            macroElementRepository.deleteAllByProduct(product);
        }
    }
}