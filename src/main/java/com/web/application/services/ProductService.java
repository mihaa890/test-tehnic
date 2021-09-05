package com.web.application.services;


import com.web.application.entity.Product;
import com.web.application.interfaces.ProductRepository;
import com.web.application.interfaces.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService  implements ProductServiceImpl {


    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long product_id) {
        Product product = productRepository.findById(product_id).orElseThrow(()->
                new IllegalStateException("Product with id: " + product_id + " not found"));
        productRepository.deleteById(product_id);
        return false;
    }

    public Product reserveProduct(Product product) {
        return productRepository.save(product);
    }
}
