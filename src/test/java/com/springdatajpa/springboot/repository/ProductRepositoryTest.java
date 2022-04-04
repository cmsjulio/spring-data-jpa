package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //this annotation loads all the spring beans from our application (full application context)
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void saveMethod(){
    // create product object
    Product product = new Product();
    product.setName("product 1");
    product.setDescription("product 1 descritpion");
    product.setSku("100ABC");
    product.setPrice(new BigDecimal(100));
    product.setActive(true);
    product.setImageUrl("product1.png");

    // save product
    Product savedObject = productRepository.save(product);

    // display product information
    System.out.println(savedObject.getId());
    System.out.println(savedObject.toString());
  }

}
