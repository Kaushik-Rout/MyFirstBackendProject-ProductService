package com.pheonix.productservicefirstproject.services;

import com.pheonix.productservicefirstproject.dtos.FakeStoreProductDto;
import com.pheonix.productservicefirstproject.models.Category;
import com.pheonix.productservicefirstproject.models.Products;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        return List.of();
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
