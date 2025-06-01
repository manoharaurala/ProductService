package org.ruby.productservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity()
@Table(name = "categories")
public class Category extends BaseModel {
    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    List<Product> products;
    //@Column(unique = true, nullable = false)
    private String title;

}
