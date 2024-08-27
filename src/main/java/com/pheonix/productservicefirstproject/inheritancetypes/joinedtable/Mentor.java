package com.pheonix.productservicefirstproject.inheritancetypes.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_mentor") //joinedtable_mentor - for identifcation purpose
@PrimaryKeyJoinColumn(name = "user_id") // to link parent class table with id
public class Mentor extends User{
    private String company;
}
