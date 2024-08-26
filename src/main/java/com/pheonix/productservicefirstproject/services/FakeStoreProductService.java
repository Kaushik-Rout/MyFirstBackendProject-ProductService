package com.pheonix.productservicefirstproject.services;

import com.pheonix.productservicefirstproject.dtos.FakeStoreProductDto;
import com.pheonix.productservicefirstproject.exceptions.ProductNotFoundException;
import com.pheonix.productservicefirstproject.models.Category;
import com.pheonix.productservicefirstproject.models.Products;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

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
    public Products getSingleProduct(Long productId) throws ProductNotFoundException {
        //Note:
        //call fakestore to fetch the details of the product with given Id : --> http call
        //for that we can use RestTemplate , others webClient ,,,etc
        //*RestTemplate restTemplate = new RestTemplate(); */ //*********
        //but
        //this way we have to create object of RestTemplate in every method o fetch data from fakestore
        //Hence, we will create a config package and create a class of RestTemplate , so that ,
        //spring will create an object of it by itself !
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class); // .class means type of data/object

        // Case: handle the situation if productId sent by client is invalid : Exception Handling.
        if (fakeStoreProductDto == null){
            // if fakestoreproductDto is pointing to a null object then code should stop here
            // we aren't caliing Nullpointer exception , we are throwing a specific  customized exception.
            throw new ProductNotFoundException("No such Product with ID: "+ productId + " exists.");
        }

        //since return type is Products
        return convertFakestoreProductDtoToProducts(fakeStoreProductDto);

    }

    @Override
    public List<Products> getAllProducts() {
        // since it will be returning a list we have to create a list of object that stores the products

        //List<FakeStoreProductDto>.class : can't be used , instead use Array
        //<FakeStoreProductDto> is the generic used here , but this line of code is compiled during Runtime
        // During Runtime , generic don't matter everything is considered as object
        //Runtime Type Checks: You can't directly check the type of a generic list at runtime using instanceof or .getClass().
        // You would need to cast the elements to their specific types and handle potential ClassCastExceptions.
        //Class Literals: The syntax List<FakeStoreProductDto>.class is incorrect. You can't use a generic type directly in a class literal.

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

    @Override
    public Products updateProduct(Long productId, Products product) {
        //restTemplate.patchForObject() //we modifies path() method of restTemplate to not just update product but also return the updated product.
        //originsl:
//        public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws
//        RestClientException {
//            RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
//            HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, this.getMessageConverters(), this.logger);
//            return this.execute(url, HttpMethod.PATCH, requestCallback, responseExtractor, (Object[])uriVariables);
//        }

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute(
                "https://fakestoreapi.com/products/" + productId, HttpMethod.PATCH,
                requestCallback, responseExtractor);

        return convertFakestoreProductDtoToProducts(response);
    }

    @Override
    public void replaceProduct(Long productId, Products product) {
        //restTemplate.put();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product);
        restTemplate.execute(
                "https://fakestoreapi.com/products/" + productId, HttpMethod.PUT,
                requestCallback, (ResponseExtractor)null);
    }

    @Override
    public void deleteProduct(Long productId) {

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
