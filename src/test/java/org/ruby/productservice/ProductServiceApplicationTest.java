package org.ruby.productservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ruby.productservice.models.Category;
import org.ruby.productservice.models.Product;
import org.ruby.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceApplicationTest {
    // This is a test class for the ProductServiceApplication
    @Autowired
    private ProductRepository productRepository;



    @BeforeEach
    public void setUp() {
        // This method will run before each test
        // You can initialize any required objects or state here
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Test Category");
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");
        product.setPrice(100.0);
        product.setDescription("Test Description");
        product.setCategory(category); // Set category to null or a valid Category object
        product.setImgUrl("http://example.com/image.jpg");
        productRepository.save(product);
    }

    @Test
    void contextLoads() {
        // This test will check if the application context loads successfully


    }

    @Test
    void testQueriesHQL() {
        // Test the queries here
        // Example: assertEquals(expected, actual);
        productRepository.findProductWithGivenIdHQL(1L)
                .ifPresent(product -> {
                    assertEquals("Test Product", product.getTitle());
                    assertEquals(100.0, product.getPrice());
                    assertEquals("Test Description", product.getDescription());
                    assertEquals("http://example.com/image.jpg", product.getImgUrl());
                });
    }

    @Test
    void testQueriesSQL() {
        // Test the queries here
        // Example: assertEquals(expected, actual);
        productRepository.findProductWithGivenIdSQL(1L)
                .ifPresent(product -> {
                    assertEquals("Test Product", product.getTitle());
                    assertEquals(100.0, product.getPrice());
                    assertEquals("Test Description", product.getDescription());
                    assertEquals("http://example.com/image.jpg", product.getImgUrl());
                });
    }



}