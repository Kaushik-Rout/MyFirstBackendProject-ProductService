package com.pheonix.productservicefirstproject.models;

import jakarta.persistence.Entity;
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
}
