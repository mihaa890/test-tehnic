package com.web.application.interfaces;


import com.web.application.entity.Product;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Long id;

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfProductIdExists(){
        Product product =  new Product(1L, "Smart TV", 350.99,10 );
        productRepository.save(product);

        Optional<Product> result =  productRepository.findById(1L);
        assertFalse(result.isEmpty());
    }

    @Test
    void itShouldCheckIfProductIdDoesNotExists(){
        Optional<Product> result =  productRepository.findById(id);
        assertTrue(result.isEmpty());
    }
}
