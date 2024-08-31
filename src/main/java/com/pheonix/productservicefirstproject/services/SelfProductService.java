package com.pheonix.productservicefirstproject.services;

import com.pheonix.productservicefirstproject.exceptions.ProductNotFoundException;
import com.pheonix.productservicefirstproject.models.Category;
import com.pheonix.productservicefirstproject.models.Products;
import com.pheonix.productservicefirstproject.repositories.CategoryRepository;
import com.pheonix.productservicefirstproject.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService") //Bean name
public class SelfProductService implements ProductService {

    private final CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    private CategoryRepository CategoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository CategoryRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.CategoryRepository = CategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Products getSingleProduct(Long productId) throws ProductNotFoundException {
        //make a call to DB to fetch the product with the requested ID
        Optional<Products> productsOptional = productRepository.findById(productId);

        if (productsOptional.isEmpty()) {
            throw new ProductNotFoundException("Product :"+productId+" not found");
        }
        return productsOptional.get();
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
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

        productRepository.deleteById(productId);

    }

    @Override
    public Products addNewProduct(Products product) {

        Category category= product.getCategory();
        // while we add a new product , first we need to create its category and save it then save the product.
        if (category.getId()==null){
            //We need to create a new category object in the DB first
            category = categoryRepository.save((category));
            //this catoegory is the output with the id set ------ save(category) is the input category with no id
            //set category for thr product object
            product.setCategory(category);
        }

        return productRepository.save(product);
    }
}
