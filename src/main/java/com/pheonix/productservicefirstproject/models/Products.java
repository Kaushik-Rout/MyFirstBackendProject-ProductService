package com.pheonix.productservicefirstproject.models;

// easy implementation of getter setter via lombok / no redundant or boiler plate code
//noneed to write getter setter method for each - in main class we can directly
// use setproductName , getId ... ets and it will do the work
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Products extends BaseModel{

    private String productName;
    private double price;
    private int quantity;
    private Category category;

}
