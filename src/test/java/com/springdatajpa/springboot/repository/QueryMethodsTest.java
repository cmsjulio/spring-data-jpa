package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QueryMethodsTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void findByNameMethod() {

    Product product = productRepository.findByName("product 2");

    System.out.println(product.getId());
    System.out.println(product.getName());
    System.out.println(product.getDescription());
  }


  @Test
  void findByIdMethod() {

    Product product = productRepository.findById(12L).get();

    System.out.println(product.getId());
    System.out.println(product.getName());
    System.out.println(product.getDescription());
  }


  @Test
  void findByNameOrDescriptionMethod() {

    // qualquer um dos dois é suficiente
    List<Product> productList = productRepository.findByNameOrDescription("product 2", "product 3 description");
    productList.forEach(System.out::println);

  }


  @Test
  void findByNameAndDescriptionMethod() {

    // sem match
    List<Product> productList = productRepository.findByNameAndDescription("product 2", "product 3 description");
    productList.forEach(System.out::println);

    // com match
    List<Product> productList2 = productRepository.findByNameAndDescription("product 2", "product 2 description");
    productList2.forEach(System.out::println);

  }


  @Test
  void findDistinctByNameMethod() {

    Product product = productRepository.findDistinctByName("product 2");
    System.out.println(product.getId());
    System.out.println(product.getName());
    System.out.println(product.getDescription());
  }


  @Test
  void findByPriceGreaterThanMethod() {

    List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(100));

    products.forEach(x->{
      System.out.println(x.getId());
      System.out.println(x.getName());
      System.out.println(x.getDescription());
      System.out.println(x.getPrice());
      System.out.println("-----------------");
    });

  }


  @Test
  void findByPriceLessThanMethod() {

    List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(250));

    products.forEach(x->{
      System.out.println(x.getId());
      System.out.println(x.getName());
      System.out.println(x.getDescription());
      System.out.println(x.getPrice());
      System.out.println("-----------------");
    });

  }

  @Test
  void findByNameContainingMethod() {

    List<Product> products = productRepository.findByNameContaining("product");

    products.forEach(x->{
      System.out.println(x.getId());
      System.out.println(x.getName());
      System.out.println(x.getDescription());
      System.out.println(x.getPrice());
      System.out.println("-----------------");
    });

  }

  @Test
  void findByNameLikeMethod() {

    // se passar "product", nao retorna tudo; há divergências.
    List<Product> products = productRepository.findByNameLike("product 1");

    products.forEach(x->{
      System.out.println(x.getId());
      System.out.println(x.getName());
      System.out.println(x.getDescription());
      System.out.println(x.getPrice());
      System.out.println("-----------------");
    });
  }


  @Test
  void findByPriceBetweenMethod() {

    // se passar "product", nao retorna tudo; há divergências.
    List<Product> products = productRepository.findByPriceBetween(new BigDecimal(100), new BigDecimal(300));

    products.forEach(x->{
      System.out.println(x.getId());
      System.out.println(x.getName());
      System.out.println(x.getDescription());
      System.out.println(x.getPrice());
      System.out.println("-----------------");
    });

  }
}
