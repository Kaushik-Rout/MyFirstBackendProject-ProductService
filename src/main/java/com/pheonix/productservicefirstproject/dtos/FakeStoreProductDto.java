package com.pheonix.productservicefirstproject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// DTO: when we want to interacts with some external data or client , we need to create some objects for it
// those objects are called as DTO
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

}
