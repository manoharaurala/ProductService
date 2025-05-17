package org.ruby.productservice.controllers;

import org.ruby.productservice.exceptions.CategoryNotFoundException;
import org.ruby.productservice.models.Product;
import org.ruby.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // Constructor based dependency injection
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId) {

        return
                new ResponseEntity<>(
                        productService.getSingleProduct(productId),
                        HttpStatus.OK
                );


    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) throws CategoryNotFoundException {
        return productService.createProduct(product);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long productId) {
        return null;
    }
}

/*
CRUD operations on Product model
 */

/*
    {
        "title" : "iPhone 15 pro",
        "description" : "best iphone ever",
        "price" : "130000",
        ....
    }


 */

//Update API's
// updateProduct() -> PATCH
// replaceProduct() -> PUT
