package org.ruby.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// This is a simple Java class representing a Product model.
@Getter
@Setter
@Entity()
@Table(name = "products")
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne
    private Category category;

}
/*
1               1
Product -------Category  => m:1
m               1


1:1 =>Id of 1 side on other side
1:m =>Id of 1 side on many side
m:m => Mapping table
 */
