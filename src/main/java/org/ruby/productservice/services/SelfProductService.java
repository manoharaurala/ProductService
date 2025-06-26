package org.ruby.productservice.services;

import org.ruby.productservice.exceptions.CategoryNotFoundException;
import org.ruby.productservice.exceptions.ProductNotFoundException;
import org.ruby.productservice.models.Category;
import org.ruby.productservice.models.Product;
import org.ruby.productservice.repositories.CategoryRepository;
import org.ruby.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "selfProductService")
@Primary
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        //Find the product with id
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId, "Product with id " + productId + " doesn't exist."));
    }

    @Override
    public List<Product> getAllProducts() {
        //Find all products
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        Category category = product.getCategory();
        if (category == null) {
            throw new CategoryNotFoundException("Product can't be created without category, Please try again with category.");
        }
        //Find the category with id
        Optional<Category> categoryOptional = categoryRepository.findByTitle(category.getTitle());
        if (categoryOptional.isEmpty()) {
            //There's no category in the DB with the given title.
            //Create a category object and save it in the DB.
            category = categoryRepository.save(category);
        } else {
            //Category exists in the DB
            //Get the category from the DB
            category = categoryOptional.get();
            //Set the category to the product

        }
        product.setCategory(category);
        return productRepository.save(product);

    }

    @Override
    public boolean deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            productRepository.delete(product);
            return true;
        } else {
            return false;
        }
    }
}
