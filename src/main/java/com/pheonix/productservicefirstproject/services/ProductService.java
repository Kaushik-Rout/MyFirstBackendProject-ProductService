package com.pheonix.productservicefirstproject.services;

import com.pheonix.productservicefirstproject.exceptions.ProductNotFoundException;
import com.pheonix.productservicefirstproject.models.Products;

import java.util.List;

public interface ProductService {
    //by default all the attributes of an interface are public or static
    Products getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Products> getAllProducts();

    Products updateProduct(Long productid, Products product);

    void replaceProduct(Long productId, Products product);

    void deleteProduct(Long productId);
}
