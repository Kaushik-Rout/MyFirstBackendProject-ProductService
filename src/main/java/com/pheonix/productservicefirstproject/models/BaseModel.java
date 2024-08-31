package com.pheonix.productservicefirstproject.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;

//creating this to have the root logs of the products created & updated
@Getter
@Setter
//we are using @MappedSuperClass because object or table of this parent class is not needed, we can use these entities in all the tables of child class.
@MappedSuperclass
public class BaseModel {
    @Id    //this annotation will make the variable id as the primary key and @GenerateValue will auto generate it.
    @GeneratedValue(strategy = GenerationType.AUTO)      //Autogenerate
    private Long id;          //PK ------- can be : (private UUID id) //non-primitive : Long - can store objects and can be null unlinke primitive long.
    private Date createdAt;
    private Date updatedAt;

}
