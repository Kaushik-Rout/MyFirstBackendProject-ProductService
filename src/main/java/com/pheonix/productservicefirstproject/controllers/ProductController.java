package com.pheonix.productservicefirstproject.controllers;

import com.pheonix.productservicefirstproject.models.Products;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    // http://localhost:8080/products/{id}
    @GetMapping("/{id}")
    public Products getProductByID(@PathVariable("id") long id) {
        return null;
    }

    public list<Products> getAllProducts(){
        return new ArrayList<>();
    }
}
