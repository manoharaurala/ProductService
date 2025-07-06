package org.ruby.productservice.controllers;

import org.ruby.productservice.commons.AuthCommonUtil;
import org.ruby.productservice.exceptions.CategoryNotFoundException;
import org.ruby.productservice.exceptions.UnAuthorisedException;
import org.ruby.productservice.models.Product;
import org.ruby.productservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final AuthCommonUtil authCommonUtil;
    private final RestTemplate restTemplate;

    // Constructor based dependency injection
    public ProductController(ProductService productService, AuthCommonUtil authCommonUtil, RestTemplate restTemplate) {
        this.productService = productService;
        this.authCommonUtil = authCommonUtil;
        this.restTemplate = restTemplate;
    }

    @GetMapping("token/{id}")
    public UserDto validateToken(String tokenValue) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", tokenValue);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        try {
            return restTemplate.exchange(
                    "http://127.0.0.1:8081/auth/validate/",
                    HttpMethod.GET,
                    httpEntity,
                    UserDto.class
            ).getBody();
        } catch (HttpServerErrorException | HttpClientErrorException ex) {
            // Log the error or handle as needed
            return null;
        }
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId, @RequestHeader("Authorization") String tokenValue) throws UnAuthorisedException {
        // Validate the token
        UserDto userDto = authCommonUtil.validateToken(tokenValue);
        if (userDto == null) {
            throw new UnAuthorisedException("Invalid token, please login again.");
        }

        return productService.getSingleProduct(productId);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/title/{title}/{pageNumber}/{pageSize}")
    public Page<Product> getProductByTitle(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("title") String title) {
        return productService.getProductByTitle(title, pageNumber, pageSize);
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
