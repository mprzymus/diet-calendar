package pl.mprm.diet_calendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprm.diet_calendar.controllers.product.ProductDto;
import pl.mprm.diet_calendar.controllers.product.ProductDtoToProduct;
import pl.mprm.diet_calendar.controllers.product.ProductToProductDto;
import pl.mprm.diet_calendar.dao.MacroElementRepository;
import pl.mprm.diet_calendar.dao.MicroElementRepository;
import pl.mprm.diet_calendar.dao.ProductRepository;
import pl.mprm.diet_calendar.model.product_data.MacroElement;
import pl.mprm.diet_calendar.model.product_data.MicroElement;
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
    private final ProductDtoToProduct toProduct;
    private final MicroElementRepository microElementRepository;
    private final MacroElementRepository macroElementRepository;

    public List<Product> findAllProducts() {
        var list = new ArrayList<Product>();
        productRepository.findAll().forEach(list::add);
        return list;
    }

    public List<ProductDto> findAllProductAsDTO() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(toProductDto::convert)
                .collect(Collectors.toList());
    }

    public Product saveCommand(ProductDto command) {
        final var product = toProduct.convert(command);
        assert product != null;
        productRepository.findById(product.getId()).map(productFromDb -> {
            var microIds = productFromDb.getMicroElements().stream().map(MicroElement::getId).iterator();
            var microIterator = product.getMicroElements().iterator();
            while (microIds.hasNext() && microIterator.hasNext()) {
                microIterator.next().setId(microIds.next());
            }
            while (microIds.hasNext()) {
                microElementRepository.deleteById(microIds.next());
            }
            var macroIds = productFromDb.getMacroElements().stream().map(MacroElement::getId).iterator();
            var macroIterator = product.getMacroElements().iterator();
            while (macroIds.hasNext() && macroIterator.hasNext()) {
                microIterator.next().setId(microIds.next());
            }
            while (macroIds.hasNext()) {
                macroElementRepository.deleteById(macroIds.next());
            }
            return productFromDb;
        });
        return productRepository.save(product);
    }

    public boolean nameExists(Long id, String name) {
        var withThisName = productRepository.findAllByName(name);
        return withThisName.iterator().hasNext() &&
                StreamSupport.stream(withThisName.spliterator(), false)
                .noneMatch(dbProduct -> dbProduct.getId().equals(id));
    }

    public ProductDto findDtoById(Long id) {
        var product = productRepository.findById(id).orElse(new Product());
        return toProductDto.convert(product);
    }
}
