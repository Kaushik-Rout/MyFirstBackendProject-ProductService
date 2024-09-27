package com.pheonix.productservicefirstproject.repositories;

import com.pheonix.productservicefirstproject.models.Products;
import com.pheonix.productservicefirstproject.projections.ProductWithIdAndTitle;
import com.pheonix.productservicefirstproject.projections.ProductWithIdTitleAndPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Rules:
// Repository should be an Interface
// Repository should extend JpaRepository

//It will contain all the CRUD operations related to Product Table
@Repository //indentifies it as repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    //JpaRepository<Products, Long> : telling JPA whats model to work on and whats the data type of the primary keu (ID in Base model is the PK in my case)
    // ProductRepository should contain all the methods {CRUD} related to Product Model

    // Queries : https://docs.spring.io/spring-data/jpa/reference/3.2-SNAPSHOT/repositories/query-keywords-reference.html#appendix.query.method.subject


    List<Products> findByPriceGreaterThan(double price);
    // select * from Products where price > ?

    List<Products> findByPriceLessThan(double price);
    // select * from Products where price < ?

    List<Products> findByTitleLike(String title); //Case Sensitive
    // select * from Products where title like "%title"

    List<Products> findByTitleLikeIgnoreCase(String title); // case insensitive

    List<Products> findTop5ByTitleContains(String word);
    // // select * from Products where title like "%title" LIMIT 5

    List<Products> findProductsByTitleContainsAndPriceGreaterThan(
            String word, double price);

    //find product that contains word and order by ID
    List<Products> findProductsByTitleContainsOrderById(String word);

    /*
    * Products p = ProductRepository.findbyID(ID);   if the value is NULL
    * return p;   -- > NullPointerException
    * Hence, use "Optional" : its like a bucket , if product is found , the bucket will contain it
    * else it will be empty.
    * */
    Optional<Products> findById(Long id);

    //GET - HTTP Call
    //JPA supports Pagination with the "Pagable" intrface - it returns a page of products
    @Override
    Page<Products> findAll(Pageable pageable);

    //D - delete a product
    //DELETE - HTTP Call
    void deleteById(Long id);

    // C & U : Create & Update a product
    //POST - HTTP Call
    Products save(Products products);

    // HQL
    // search only for id and titls of the products
    @Query("select p.id as Id, p.title as Title from Products p ")
    List<ProductWithIdAndTitle> fetchIdAndTitle();

    //Search for id , title & price of the products
    @Query("select p.id as Id, p.title as Title, p.price as Price from Products p")
    List<ProductWithIdTitleAndPrice> fetchIdTitleAndPrice();

    // fetch with particular id fetch from user ":x"(where we want to mention it in query
    @Query("select p.id as Id, p.title as Title, p.price as Price from Products p where p.id =:x")
    //here the return type ProductWithIdTitleAndPrice is not a list cause it will get only 1  product
    ProductWithIdTitleAndPrice fetchIdTitlePriceWithId(Long x);

}
