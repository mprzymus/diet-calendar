package pl.mprm.diet_calendar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mprm.diet_calendar.model.product_data.Product;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
class ElementsServiceIT {

    @Autowired
    private ElementsService elementsService;

    @Test
    void deleteElementsOnNotSavedProduct() {
        var product = new Product();
        assertDoesNotThrow(() -> elementsService.deleteProductsElements(product));
    }
}