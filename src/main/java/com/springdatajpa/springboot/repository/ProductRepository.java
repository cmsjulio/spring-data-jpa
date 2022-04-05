package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

  public Product findByName(String name);

  Optional<Product> findById(Long id);

  public List<Product> findByNameOrDescription(String name, String description);

  public List<Product> findByNameAndDescription(String name, String description);

  // se nenhum produto for encotrado, retorna vazio;
  public Product findDistinctByName (String name);

  List<Product> findByPriceGreaterThan(BigDecimal price);

  List<Product> findByPriceLessThan(BigDecimal price);

}
