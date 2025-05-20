package org.ruby.productservice.inheritanceDemo.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_tas")
@DiscriminatorValue("1")
public class TA extends User {
    private int numberOfHR;
}
