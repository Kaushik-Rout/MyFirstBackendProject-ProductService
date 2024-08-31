package com.pheonix.productservicefirstproject.controllers;

import com.pheonix.productservicefirstproject.exceptions.ProductNotFoundException;
import com.pheonix.productservicefirstproject.models.Products;
import com.pheonix.productservicefirstproject.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
//Spring will create an object of all classes but "ProductService" is an interface how can we create an object of it ?
    // Spring has to create an object of class which implements "ProductService" i.e. FakeStoreProductService
    // using @Service annotation - ask spring to create the object of FakeStore... class /SelfProductService class

    // We are not using @Primary annotation cause if we have to change is we have to move to the particular class and change it
    // @Qualifier annotation in the constructor for prioritizing the primary class to be linked to the controller
    public ProductController(@Qualifier("selfProductService") ProductService productService){

        this.productService = productService;
    }

    //FakeStore link : ('https://fakestoreapi.com/products/1')
    // http://localhost:8080/products/{id}
    //ResponseEntity send the server ststus like 200 good request, 404 bad request etc...
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductByID(@PathVariable("id") long id) throws ProductNotFoundException {

        //return productService.getSingleProduct(id);

//        Exception Handling : -
//        ResponseEntity<Products> responseEntity = null;

//        try {
//            responseEntity = new ResponseEntity<>(
//                    productService.getSingleProduct(id),
//                    HttpStatus.OK
//            );
//        }catch(RuntimeException e){
//            responseEntity = new ResponseEntity<>(
//                    HttpStatus.NOT_FOUND);
//
//        }

        ResponseEntity<Products> responseEntity = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        ); //OK - 200 means good request, BAD_REQUES - 400T means bad request, FORBIDDEN - 403 forbidden

        return responseEntity;
    }

    // FakeStore link : ('https://fakestoreapi.com/products')
    // Since /products is already in  RequestMapping annotation.
    @GetMapping()
    public List<Products> getAllProducts(){

        return productService.getAllProducts();

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

        productService.deleteProduct(id);

    }
    //PATCH request - partial update , will update the product , incase there is an attribute not define by the user , I would keep the original attribute as its data.
    // PATCH -> http://localhost:8080/products/{id}
    // @RequestBody : takes the product as input from the client
    @PatchMapping("{id}")
    public Products updateProduct(@PathVariable("id") long id, @RequestBody Products product) {

        return productService.updateProduct(id, product);

    }
    //PUT request - complete replace, will update the product , incase there is an attribute not define by the user , I would set it as Null.
    // PUT -> http://localhost:8080/products/{id}
    @PutMapping("/{id}")
    public Products replaceProduct(@PathVariable("id") long id, @RequestBody Products product) {

        return null;
    }

    @PostMapping
    public Products addProduct(@RequestBody Products product) {
        return productService.addNewProduct(product);
    }

//    This gets more priority than controller advice , It kind of overrides controler advice Runtime excption handler
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handlerArithmaticException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "Arithmatic Exception happened, Calling from controller",
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

}
