## Spring Data JPA

### Spring Data
Spring Data Commons is the super class from which many others derive.

Spring Data JPA, Spring Data MongoDB, Spring Data JDBC, all derive from Spring Data Commons.

The focus of this course is Spring Data JPA.

Check below the class diagram:

![img.png](img.png)

And more, for the JpaRepository relations:

![img_1.png](img_1.png)

Once we do as below:

![img_2.png](img_2.png)

We have access to all the methods from all shown interfaces.

### Where is the implementation?

Spring Data JPA is the one that implements all of the methods. 

It is dealt with in the SimpleJpaRepositoy class.

As below:

![img_3.png](img_3.png)

All the methods in the SimpleJpaRepositoy class are @Transactional.

### Class location
The file "SimpleJpaRepository.class" is in the support directory, under External Files of spring-data-jpa library.

There we see a class that implements the JpaRepositoryImplementation interface (which implemented JpaRepository interface).

![img_4.png](img_4.png)
