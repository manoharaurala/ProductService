package org.ruby.productservice.controllers;

import org.ruby.productservice.commons.AuthCommonUtil;
import org.ruby.productservice.exceptions.CategoryNotFoundException;
import org.ruby.productservice.exceptions.UnAuthorisedException;
import org.ruby.productservice.models.Product;
import org.ruby.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final AuthCommonUtil authCommonUtil;

    // Constructor based dependency injection
    public ProductController(ProductService productService, AuthCommonUtil authCommonUtil) {
        this.productService = productService;
        this.authCommonUtil = authCommonUtil;
    }

    @GetMapping("{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId,@RequestHeader("Authorization") String token) {
        UserDto userDto = authCommonUtil.validateToken(token);
        if (userDto == null) {
            throw new UnAuthorisedException("Invalid token provided");
        }
        return productService.getSingleProduct(productId);
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
        boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
