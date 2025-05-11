package org.ruby.productservice.models;

import lombok.Getter;
import lombok.Setter;

// This is a simple Java class representing a Product model.
@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;

}
