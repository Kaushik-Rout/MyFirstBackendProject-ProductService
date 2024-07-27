package com.pheonix.productservicefirstproject;

import com.pheonix.productservicefirstproject.models.Products;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceFirstProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductServiceFirstProjectApplication.class, args);

		Products product = new Products();
		// product.proctName = // not allowed cause its private.
		product.setTitle("iphone");
	}

}
