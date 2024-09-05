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
    //GET
    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }
    //PATCH
    @Override
    public Products updateProduct(Long productid, Products product) throws ProductNotFoundException {
        //getting the product to update
        Optional<Products> productOptional = productRepository.findById(productid);
        //if not found
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with ID" + productid + "not found");
        }

        Products productinDB = productOptional.get();
        //checking what all update in the product is being requested
        if (product.getTitle() != null){
            productinDB.setTitle(product.getTitle());
        }

        if (product.getPrice() != null){
            productinDB.setPrice(product.getPrice());
        }

        if (product.getQuantity() != null){
            productinDB.setQuantity(product.getQuantity());
        }

        return productRepository.save(productinDB);

    }
    //PUT
    @Override
    public void replaceProduct(Long productid, Products product) throws ProductNotFoundException {
        //getting the product to update
        Optional<Products> productOptional = productRepository.findById(productid);
        //if not found
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with ID" + productid + "not found");
        }
        Products productinDB = productOptional.get();

        //getting and setting the category
        Category category = product.getCategory();
        category = categoryRepository.save(category);
        productinDB.setCategory(category);

        //or -depending on situations
        //getting and setting the category
//        if(product.getCategory()!=null){
//            Category category = product.getCategory();
//            if(category.getId()==null){
//                category = categoryRepository.save(category);
//            }
//            productinDB.setCategory(category);
//        }
        //setting all new values to replace
        productinDB.setTitle(product.getTitle());
        productinDB.setPrice(product.getPrice());
        productinDB.setQuantity(product.getQuantity());
        productinDB.setImage(null);

        productRepository.save(productinDB);
    }
    //DELETE
    @Override
    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);

    }
    //POST
    @Override
    public Products addNewProduct(Products product) {
        //Note:
//we can do the below of we can use "(cascade = CascadeType.PERSIST)" as used in the product model to create category when product is created /added
//        Category category= product.getCategory();
//        // while we add a new product , first we need to create its category and save it then save the product.
//        if (category.getId()==null){
//            //We need to create a new category object in the DB first
//            category = categoryRepository.save((category));
//            //this catoegory is the output with the id set ------ save(category) is the input category with no id
//            //set category for thr product object
//            product.setCategory(category);
//        }
        return productRepository.save(product);
    }
}
