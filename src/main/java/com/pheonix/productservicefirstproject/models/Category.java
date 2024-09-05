package com.pheonix.productservicefirstproject.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

//for categories , since it can change so many times in an e-commerce platform,
//enum is not a good idea , hence we are defining as a class.
@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String categoryName;
    private String description;
    @OneToMany(mappedBy = "category") //This means this is related to ManyToOne relation of category in Product table. Don't make productlist column on category table or a mapping table of Category and product.
    private List<Products> products;
}
