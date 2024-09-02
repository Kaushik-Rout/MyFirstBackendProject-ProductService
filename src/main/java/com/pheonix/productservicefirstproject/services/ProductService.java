package com.pheonix.productservicefirstproject.services;

import com.pheonix.productservicefirstproject.exceptions.ProductNotFoundException;
import com.pheonix.productservicefirstproject.models.Products;

import java.util.List;

//Since this interface is being used bu 2 classes, @Primary annotation will help prioritize which class should use it.
public interface ProductService {
    //by default all the attributes of an interface are public or static
    Products getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Products> getAllProducts();

    Products updateProduct(Long productid, Products product) throws ProductNotFoundException;

    void replaceProduct(Long productId, Products product) throws ProductNotFoundException;

    void deleteProduct(Long productId);

    Products addNewProduct(Products product);

}
