package pl.mprm.diet_calendar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprm.diet_calendar.dao.ProductRepository;
import pl.mprm.diet_calendar.model.product_data.Product;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void findAllProductsForEmptyDatabase() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        var allProducts = productService.findAllProducts();

        assertEquals(0, allProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findAllProductsTest() {
        var prod1 = new Product();
        prod1.setId(1L);
        var prod2 = new Product();
        prod2.setId(2L);

        when(productRepository.findAll()).thenReturn(List.of(prod1, prod2));

        var allProducts = productService.findAllProducts();

        assertEquals(2, allProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void nameNotExistsTest() {
        when(productRepository.findAllByName(anyString())).thenReturn(Collections.emptyList());

        assertFalse(productService.nameExists("name"));

        verify(productRepository, times(1)).findAllByName(anyString());
    }

    @Test
    void nameExists() {
        when(productRepository.findAllByName(anyString())).thenReturn(List.of(new Product()));

        assertTrue(productService.nameExists("name"));

        verify(productRepository, times(1)).findAllByName(anyString());
    }
}