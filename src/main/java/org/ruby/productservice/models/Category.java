package org.ruby.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity()
@Table(name = "categories")
public class Category extends BaseModel {
    //@Column(unique = true, nullable = false)
    private String title;

}
