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

  @Test
  void updateUsingSaveMethod(){
    //find or retrieve and entity by ID from the database
    Long id = 1L;
    Product product = productRepository.findById(id).get();

    //update entity information
    product.setName("updated product 1");
    product.setDescription("updated product 1 description");

    //save updated entity into the database table
    productRepository.save(product);

    //in this case, because product1 already exists and has a primary key, the save method will
    //internally call the merge() method to update it instead of saving it.
    System.out.println(product);
  }

  @Test
  void findByIdMethod(){
    Long id = 1L;

    Product product = productRepository.findById(id).get();
  }

}
