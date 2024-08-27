package com.pheonix.productservicefirstproject.models;

// easy implementation of getter setter via lombok / no redundant or boiler plate code
//noneed to write getter setter method for each - in main class we can directly
// use setproductName , getId ... ets and it will do the work
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//the annotation @Entity will create tables for the product entities automatically by Hibernate.
@Entity
//class to store data of product objects
public class Products extends BaseModel{
    private long id;
    private String title;
    private double price;
    private int quantity;
    private String image;
    @ManyToOne //in product table Category column will be made as Category _ID and it will become a Foreign Kay constraint
    private Category category;
    /*
            1                    1     (1 product can have 1 category)
        Products <----------> Category
            M                    1      (1 Category can have many products e.g. category : phone )
*/

}

