package com.web.application.controllers;


import com.web.application.entity.Product;
import com.web.application.interfaces.ProductRepository;
import com.web.application.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/get-products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/create-new-product/")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/edit-product/{product_Id}")
    public ResponseEntity<?> editProduct(@PathVariable("product_Id") Long product_Id, @RequestBody Product product) {

        product.setId(product_Id);

        Product editProduct = productService.editProduct(product);
        return new ResponseEntity<Object>(editProduct, HttpStatus.OK);

    }

    @DeleteMapping(path = "/delete-product/{product-Id}")
    public void deleteProduct(@PathVariable("product-Id") Long product_Id) {
        productService.deleteProduct(product_Id);
    }


    @PostMapping("/reserve-product")
    public ResponseEntity<?> reserveProduct(@RequestBody Product product) {
        product.setReserved(true);
        Product reserveProduct = productService.reserveProduct(product);
        return new ResponseEntity<Object>(reserveProduct , HttpStatus.OK);
    }
}

