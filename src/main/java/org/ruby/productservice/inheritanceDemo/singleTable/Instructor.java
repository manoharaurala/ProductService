package org.ruby.productservice.inheritanceDemo.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructors")
@DiscriminatorValue("3")
public class Instructor extends User {
    private String subject;
    private String rating;
}
