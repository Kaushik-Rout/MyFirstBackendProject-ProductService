package com.pheonix.productservicefirstproject.services;

import com.pheonix.productservicefirstproject.exceptions.ProductNotFoundException;
import com.pheonix.productservicefirstproject.models.Products;
import com.pheonix.productservicefirstproject.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService") //Bean name
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Products getSingleProduct(Long productId) throws ProductNotFoundException {
        //make a call to DB to fetch the product with the requested ID
        return null;
    }

    @Override
    public List<Products> getAllProducts() {
        return List.of();
    }

    @Override
    public Products updateProduct(Long productid, Products product) {
        return null;
    }

    @Override
    public void replaceProduct(Long productId, Products product) {

    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public Products addNewProduct(Products product) {
        return null;
    }
}
