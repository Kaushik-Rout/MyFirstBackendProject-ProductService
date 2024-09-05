package com.pheonix.productservicefirstproject.models;

// easy implementation of getter setter via lombok / no redundant or boiler plate code
//noneed to write getter setter method for each - in main class we can directly
// use setproductName , getId ... ets and it will do the work
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//the annotation @Entity will create tables for the product entities automatically by Hibernate.
@Entity
@Table(name = "products")
//class to store data of product objects
public class Products extends BaseModel {
    private String title;
    private Double price;
    private Integer quantity;
    private String image;
    //in product table Category column will be made as Category_ID and it will become a Foreign Kay constraint
    @ManyToOne(cascade = CascadeType.PERSIST) // means make a category object when a new product object is created or added to the table .
    private Category category;
    // Other types:     (cascade = CascadeType.PERSIST, CascadeType.DELETE )  OR --> (cascade = CascadeType.ALL)

    /*
            1                    1     (1 product can have 1 category)
        Products <----------> Category
            M                    1      (1 Category can have many products e.g. category : phone )
*/

}

