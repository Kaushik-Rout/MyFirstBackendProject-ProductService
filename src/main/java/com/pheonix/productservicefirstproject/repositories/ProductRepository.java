package com.pheonix.productservicefirstproject.repositories;

import com.pheonix.productservicefirstproject.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//It will contain all the CRUD operations related to Product Table
@Repository //indentifies it as repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    //JpaRepository<Products, Long> : telling JPA whats model to work on and whats the data type of the primary keu (ID in Base model is the PK in my case)
    // ProductRepository should contain all the methods {CRUD} related to Product Model

}
