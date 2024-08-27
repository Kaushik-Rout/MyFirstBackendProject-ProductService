package com.pheonix.productservicefirstproject.inheritancetypes.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_student") //joinedtable_student - for identifcation purpose
@PrimaryKeyJoinColumn(name = "user_id") // to link parent class table with id
public class Student extends User{
    private String batch;
}
