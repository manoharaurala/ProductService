package org.ruby.productservice.services;

import org.ruby.productservice.exceptions.ProductNotFoundException;
import org.ruby.productservice.models.Product;

import java.util.List;


public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product);

    boolean deleteProduct(Long productId);

}
