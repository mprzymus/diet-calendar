package pl.mprm.diet_calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mprm.diet_calendar.controllers.product.ProductDto;
import pl.mprm.diet_calendar.controllers.product.ProductCommandToProduct;
import pl.mprm.diet_calendar.controllers.product.ProductToProductDto;
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
    private final ProductToProductDto toProductDto;
    private final ProductCommandToProduct toProduct;
    private final ElementsService elementsService;

    public List<Product> findAllProducts() {
        var list = new ArrayList<Product>();
        productRepository.findAll().forEach(list::add);
        return list;
    }

    public List<ProductDto> findAllProductsAsCommand() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(toProductDto::convert)
                .collect(Collectors.toList());
    }

    public Product saveCommand(ProductDto command) {
        var product = toProduct.convert(command);
        assert product != null;
        product = elementsService.deleteProductsElements(product);
        return productRepository.save(product);
    }

    public boolean nameExists(String name) {
        return productRepository.findAllByName(name).iterator().hasNext();
    }

    public ProductDto findDtoById(Long id) {
        var product = productRepository.findById(id).orElse(new Product());
        return toProductDto.convert(product);
    }
}
