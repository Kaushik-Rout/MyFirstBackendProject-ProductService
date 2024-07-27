package com.pheonix.productservicefirstproject.services;

import com.pheonix.productservicefirstproject.models.Products;

import java.util.List;

public interface ProductService {
    //by default all the attributes of an interface are public or static
    Products getSingleProduct(Long productId);

    List<Products> getAllProducts();
}
