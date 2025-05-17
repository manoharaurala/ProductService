package org.ruby.productservice.services;

import org.ruby.productservice.exceptions.ProductNotFoundException;
import org.ruby.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "selfProductService")
//@Primary
public class SelfProductService implements ProductService {
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
