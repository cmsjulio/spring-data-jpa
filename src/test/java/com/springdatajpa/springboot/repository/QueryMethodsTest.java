package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class QueryMethodsTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void findByNameMethod(){

    Product product = productRepository.findByName("product 2");

    System.out.println(product.getId());
    System.out.println(product.getName());
    System.out.println(product.getDescription());
  }


  @Test
  void findByIdMethod(){

    Product product = productRepository.findById(12L).get();

    System.out.println(product.getId());
    System.out.println(product.getName());
    System.out.println(product.getDescription());
  }

}
