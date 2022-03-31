package com.springdatajpa.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products",
  schema = "ecommerce", //schema=database;
  uniqueConstraints = {
        @UniqueConstraint( //não pode haver dois SKUs iguais;
          name="sku_unique",
          columnNames = "stock_keeping_unit"
        )
  })
public class Product {


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
  @SequenceGenerator(name = "product_generator", sequenceName = "product_sequence_name", allocationSize = 1)
  private Long id;

  /*

  GenerationType.AUTO -> default, lets the persitence provider choose the generation strategy;
  if using Hibernate as the persistence provider, it selects a strategy based on the database-specific dialect;
  for most popular databases, it selects GenerationType.SEQUENCE.

  Hibernate creates a separate table (sequence table) to maintain sequences as primary keys -- mySql, other db might have other mechanisms;

  -

  GenerationType.IDENTITY
  it relies on an auto-incremented database column and lets the database generate a new value with each insert operation;
  from the DB standpoint, it is very efficient, as such auto-increment collumns are highly optimized;
  not good for JDBC batch operations;

    ex.:  create table products (
       id bigint not null auto_increment,
       ...

  -

  GenerationType.SEQUENCE
  the most commonly used generation strategy in large applications;
  if the application is huge, it is good to use this strategy;
  it uses a database sequence to generate unique values;

  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
  @SequenceGenerator(name = "product_generator", sequenceName = "product_sequence_name",
  allocationSize = 1) //by default, allocationSize is 50.
  //the name created in @SequenceGenerator#name should be the same in @GeneratedValue#generator;

  SEQUENCE requires addition select statements to get the next value from a databse sequence.
  But this has no performance impact on most applications.

  Most DB support it: PostGres, Oracle, MySQL.

  Hibernate:
  create table product_sequence_name (
       next_val bigint
    ) engine=InnoDB

  Cada insert novo ele busca na product_sequence_name pelo ID a ser passado e atualiza o ID.

  -

  GenerationType.TABLE
  rarely used nowadays;
  it slows down your application;

  -

  HIBERNATE suggests us to use SEQUENCE.




   */

  @Column(name="stock_keeping_unit", nullable = false, length = 256)
  private String sku;

  @Column(nullable = false)
  private String name;
  private String description;
  private BigDecimal price;
  private boolean active;
  private String imageUrl;

  @CreationTimestamp //insere data da criação no atributo automaticamente;
  private LocalDateTime dateCreated;

  @UpdateTimestamp //insere/atualiza data do último update do atributo automaticamente;
  private LocalDateTime lastUpdated;


}
