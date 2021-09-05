package com.web.application.services;

import com.web.application.entity.Product;
import com.web.application.interfaces.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock private ProductRepository productRepository;
    private AutoCloseable autoCloseable;
    private ProductService productServiceTest;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        productServiceTest = new ProductService(productRepository);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canFindAllProducts() {
        productServiceTest.findAllProducts();
        verify(productRepository).findAll();
    }

    @Test
    void deleteProduct()  {

        Product product = new Product(1L,"SmartTv",129.99, 1);
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        productServiceTest.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
    @Test
    void willThrowProductWithIdNotFound(){
        Product product = new Product(1L,"SmartTv",129.99, 1);
        assertThatThrownBy(()->productServiceTest.deleteProduct(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Product with id: " + product.getId() + " not found");

    }

}