package pl.mprm.diet_calendar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import pl.mprm.diet_calendar.dao.ProductRepository;
import pl.mprm.diet_calendar.model.product_data.Makroskladnik;
import pl.mprm.diet_calendar.model.product_data.Mikroskladnik;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
class ElementsServiceIT {

    @Autowired
    private ElementsService elementsService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void deleteElementsOnNotSavedProduct() {
        var product = new Product();
        assertDoesNotThrow(() -> elementsService.deleteProductsElements(product));
    }

    @Transactional
    @Test
    void deleteExistingProductElements() {
        var product = new Product();
        var mi1 = new Mikroskladnik();
        var mi2 = new Mikroskladnik();
        var ma1 = new Makroskladnik();
        var ma2 = new Makroskladnik();

        product.setMakroskladniki(Set.of(ma1, ma2));
        product.setMikroskladniki(Set.of(mi1, mi2));

        product = productRepository.save(product);

        elementsService.deleteProductsElements(product);

        product.setMikroskladniki(new HashSet<>());
        product.setMakroskladniki(new HashSet<>());
        product = productRepository.save(product);

        product = productRepository.findById(product.getId()).get();
        assertEquals(1, List.of(productRepository.findAll()).size());

        var saved = productRepository.findById(product.getId()).get();

        assertEquals(0, saved.getMakroskladniki().size());
        assertEquals(0, saved.getMikroskladniki().size());


    }
}