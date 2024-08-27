package com.pheonix.productservicefirstproject.inheritancetypes.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_user") //joinedtable_user - for identifcation purpose
@Inheritance(strategy = InheritanceType.JOINED) //for joined table
public class User {
    @Id
    private long id; //PK
    private String name;
    private String email;
}
