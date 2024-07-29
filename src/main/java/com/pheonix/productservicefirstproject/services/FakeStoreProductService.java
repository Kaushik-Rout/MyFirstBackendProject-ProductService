package com.pheonix.productservicefirstproject.services;

import com.pheonix.productservicefirstproject.dtos.FakeStoreProductDto;
import com.pheonix.productservicefirstproject.models.Category;
import com.pheonix.productservicefirstproject.models.Products;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

// using @Service annotation - ask spring to create the object of this class
//spring - dev has makrked this class as special class so I need to store the object of this class in the application context
//other options - @Controller / @Component / @Repository etc. Since its a service class we marked it as @Service
@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Products getSingleProduct(Long productId) {
        //Note:
        //call fakestore to fetch the details of the product with given Id : --> http call
        //for that we can use RestTemplate , others webClient ,,,etc
        /*RestTemplate restTemplate = new RestTemplate(); */ //*********
        //but
        //this way we have to create object of RestTemplate in every method o fetch data from fakestore
        //Hence, we will create a config package and create a class of RestTemplate , so that ,
        //spring will create an object of it by itself !
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class); // .class means type of data/object

        //since return type is Products
        //convert fakeStoreProductDto into product type.
        return convertFakestoreProductDtoToProducts(fakeStoreProductDto);
    }

    @Override
    public List<Products> getAllProducts() {
        // since it will be returning a list we have to create a list of object that stores the products

        //List<FakeStoreProductDto> : can't be used , instead use Array
        //<FakeStoreProductDto> is the generic used here , but this line of code is compiled during Runtime
        // During Runtime , generic don't matter everything is considered as object
        //Runtime Type Checks: You can't directly check the type of a generic list at runtime using instanceof or .getClass().
        // You would need to cast the elements to their specific types and handle potential ClassCastExceptions.

        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        //convert list of fakeStoreDtos into a list of Products
        List<Products> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakestoreProductDtoToProducts(fakeStoreProductDto));
        }

        return products;

    }

    private Products convertFakestoreProductDtoToProducts(FakeStoreProductDto fakeStoreProductDto) {
        //converting fakeStoreProductDto into product type.
        Products product = new Products();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());

        Category category=new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(category);

        return product;
    }
}
