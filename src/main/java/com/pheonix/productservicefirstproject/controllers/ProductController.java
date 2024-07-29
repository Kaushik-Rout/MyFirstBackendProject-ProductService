package com.pheonix.productservicefirstproject.controllers;

import com.pheonix.productservicefirstproject.models.Products;
import com.pheonix.productservicefirstproject.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
//Spring will create an object of all classes but "ProductService" is an interface how can we create an object of it ?
    // Spring has to create an object of class which implements "ProductService" i.e. FakeStoreProductService
    // using @Service annotation - ask spring to create the object of FakeStore... class
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //FakeStore link : ('https://fakestoreapi.com/products/1')
    // http://localhost:8080/products/{id}
    @GetMapping("/{id}")
    public Products getProductByID(@PathVariable("id") long id) {

        return productService.getSingleProduct(id);
    }

    // FakeStore link : ('https://fakestoreapi.com/products')
    // Since /products is already in  RequestMapping annotation.
    @GetMapping()
    public List<Products> getAllProducts(){

        return productService.getAllProducts();
    }
}
