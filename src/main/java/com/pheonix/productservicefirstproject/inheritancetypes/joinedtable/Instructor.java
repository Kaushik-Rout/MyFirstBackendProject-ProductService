package com.pheonix.productservicefirstproject.inheritancetypes.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_instructor") //joinedtable_instructor - for identifcation purpose
@PrimaryKeyJoinColumn(name = "user_id") // to link parent class table with id
public class Instructor extends User{
    private String subject;
}
