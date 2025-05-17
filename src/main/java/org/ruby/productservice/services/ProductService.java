package org.ruby.productservice.services;

import org.ruby.productservice.exceptions.CategoryNotFoundException;
import org.ruby.productservice.exceptions.ProductNotFoundException;
import org.ruby.productservice.models.Product;

import java.util.List;


public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product) throws CategoryNotFoundException;

    boolean deleteProduct(Long productId);

}
