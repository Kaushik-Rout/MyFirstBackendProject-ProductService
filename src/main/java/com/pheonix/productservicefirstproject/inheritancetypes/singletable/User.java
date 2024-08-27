package com.pheonix.productservicefirstproject.inheritancetypes.singletable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// since it will create just one Single table with all the classes , we need to differentiat which is a mentor , student, instructor
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.INTEGER
)
@DiscriminatorValue(value = "0")
public class User {
    @Id
    private long id;
    private String name;
    private String email;
}
