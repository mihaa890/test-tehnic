package com.web.application.interfaces;

import com.web.application.entity.Product;

import java.util.List;

public interface ProductServiceImpl {

    List<Product> findAllProducts();
    Product editProduct(Product product);
}
