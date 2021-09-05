package com.web.application.components;

import com.web.application.entity.Product;
import com.web.application.interfaces.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) {

        productRepository.saveAll(Arrays.asList(

                new Product(1L,"Smart TV",2599.5,3),
                new Product(3L,"Game Console",1234.2,10),
                new Product(3L,"Oculus",300.99,5),
                new Product(5L,"iPhone",120.99,1)
                ));

        System.out.println("----------All Data saved into Database------------------");
    }

}
