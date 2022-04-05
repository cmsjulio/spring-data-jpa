package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //this annotation loads all the spring beans from our application (full application context)
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void saveMethod() {
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
  void updateUsingSaveMethod() {
    //find or retrieve and entity by ID from the database
    Long id = 9L;
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
  void findByIdMethod() {
    Long id = 1L;

    Product product = productRepository.findById(id).get();

    //observar saída do teste para ver quais são os comandos SQL que o Hibernate gera no dialeto MYSQL.
  }

  @Test
  void saveAllMethod() {

    Product product1 = new Product();
    product1.setName("product 1");
    product1.setDescription("product 1 description");
    product1.setSku("100ABC");
    product1.setPrice(new BigDecimal(100));
    product1.setActive(true);
    product1.setImageUrl("product1.png");

    Product product2 = new Product();
    product2.setName("product 2");
    product2.setDescription("product 2 description");
    product2.setSku("100ABCD");
    product2.setPrice(new BigDecimal(200));
    product2.setActive(true);
    product2.setImageUrl("product2.png");

    Product product3 = new Product();
    product3.setName("product 3");
    product3.setDescription("product 3 description");
    product3.setSku("100ABCDE");
    product3.setPrice(new BigDecimal(300));
    product3.setActive(true);
    product3.setImageUrl("product3.png");

    productRepository.saveAll(List.of(product1, product2, product3));

    //pra ver como o método funciona e quais comandos SQL são criados, checar output do teste do método.

  }

  @Test
  void findAllMethod() {

    List<Product> products = productRepository.findAll();

    products.forEach(x -> System.out.println(x.toString()));

    //rodar método e checar output do Hibernated para ver comando SQL gerado no dialeto MYSQL;

  }

  @Test
  void deleteByIdMethod() {
    Long id = 1L;
    productRepository.deleteById(id);

    // dois comandos sao gerados: selecionar por id e depois deletar (no MySQL, pelo Hibernate)
  }

  @Test
  void deleteMethod() {

    // find an entity by id
    Long id = 2L;
    Product product = productRepository.findById(id).get();

    // delete(entity)
    productRepository.delete(product);

    //delete precisa do antecedente findById e ativa 2 comandos SQL, somando 3;
    //deleteById usa 2 comandos apenas;
    //sempre que precisar deletar alguma entidade, utilizar deleteById, por gerar menos comandos SQL;
  }

  @Test
  void deleteAll() {
    productRepository.deleteAll();
    //it first selects all, then it creates one sql statement for each object to be deleted by passing its ID;
  }

  @Test
  void deleteAllFromList() {
    Product product = productRepository.findById(5L).get();
    Product product2 = productRepository.findById(6L).get();

    productRepository.deleteAll(List.of(product, product2));
    //especificando quais entidades do banco de dados devem ser deletados;

  }

  @Test
  void countMethod() {
    long count = productRepository.count();
    System.out.println(count);

    /*
    comando de saída:

    Hibernate:
    select
        count(*) as col_0_0_
    from
        products product0_
    0
     */
  }

  @Test
  void existsByIdMethod() {
    Long id = 9L;
    boolean result = productRepository.existsById(id);

    System.out.println(result);
  }

}
