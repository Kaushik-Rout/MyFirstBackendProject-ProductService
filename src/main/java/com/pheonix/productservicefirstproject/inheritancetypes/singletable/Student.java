package com.pheonix.productservicefirstproject.inheritancetypes.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // so that huber nate will know even though its not a table since client is using SingleTable inheritance , make it a part of that table
@DiscriminatorValue(value = "1")
public class Student extends User {
    private String batch;
}
