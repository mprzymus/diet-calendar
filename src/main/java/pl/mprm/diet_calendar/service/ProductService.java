package pl.mprm.diet_calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mprm.diet_calendar.controllers.product.ProductCommand;
import pl.mprm.diet_calendar.controllers.product.ProductCommandToProduct;
import pl.mprm.diet_calendar.controllers.product.ProductToProductCommand;
import pl.mprm.diet_calendar.dao.ProductRepository;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductToProductCommand toProductCommand;
    private final ProductCommandToProduct toProduct;
    private final ElementsService elementsService;

    public List<Product> findAllProducts() {
        var list = new ArrayList<Product>();
        productRepository.findAll().forEach(list::add);
        return list;
    }

    public List<ProductCommand> findAllProductsAsCommand() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(toProductCommand::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public Product saveCommand(ProductCommand command) {
        var product = toProduct.convert(command);
        assert product != null;
        elementsService.deleteProductsElements(product);
        return productRepository.save(product);
    }
}
