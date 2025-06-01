package org.ruby.productservice.controllers;

import org.junit.jupiter.api.Test;
import org.ruby.productservice.exceptions.ProductNotFoundException;
import org.ruby.productservice.models.Product;
import org.ruby.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    // Assuming you have a ProductService mock or stub set up for testing
    @MockitoBean
    private ProductService productService;
    // The controller to be tested
    @Autowired
    private ProductController productController;

    @Test
    void getAllProducts() {
    }

    @Test
    void testGetSingleProductPositiveCase() {
        //Arranging the expected product
        Long productId = 10L;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        expectedProduct.setTitle("Iphone 17 Plus");
        expectedProduct.setPrice(65000.00);
        //Act
        when(productService.getSingleProduct(productId)).thenReturn(expectedProduct);
        //Asserting the result
        Product actualProduct = productController.getSingleProduct(productId);
        assertEquals(expectedProduct, actualProduct);
        assertEquals(productId, actualProduct.getId());
        assertEquals("Iphone 17 Plus", actualProduct.getTitle());
        assertEquals(65000.00, actualProduct.getPrice());

    }

    //TODO: Implement the negative test case for getSingleProduct

    @Test
    void testGetSingleProductNegativeCase() {
        // Arrange
        Long productId = 999L; // Assuming this product does not exist
        when(productService.getSingleProduct(productId)).thenThrow(new ProductNotFoundException(productId, "Product not found"));

        // Act & Assert
        try {
            productController.getSingleProduct(productId);
        } catch (ProductNotFoundException e) {
            assertEquals(productId, e.getProductId());
            assertEquals("Product not found", e.getMessage());
        }

    }

    @Test
    public void testGetSingleProductThrowsProductNotFoundException() throws ProductNotFoundException {
        // Arrange
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(-1L, "Please pass the correct productId");
        //Act
        when(productService.getSingleProduct(-1L))
                .thenThrow(productNotFoundException);

        Exception exception = assertThrows(ProductNotFoundException.class,
                () -> productController.getSingleProduct(-1L));

        assertEquals(productNotFoundException.getMessage(), exception.getMessage());
    }


    //TODO: Implement test case for getAllProducts
    @Test
    void testGetAllProductsPositiveCase() {
        // Arrange
        // Assuming you have a list of products to return
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("IPhone 15 Plus");
        product1.setDescription("Released in 2023");
        product1.setPrice(70000.00);
        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Iphone 16 Plus");
        product2.setDescription("Released in 2024");
        product2.setPrice(85000.00);

        List<Product> expectedProducts = List.of(product1, product2);
        // Mocking the service call to return the expected products
        // Act
        when(productService.getAllProducts()).thenReturn(expectedProducts);


        List<Product> actualProducts = productController.getAllProducts();

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void testGetAllProductsNegativeCase() {
        // Arrange
        // Mocking the service call to return an empty list
        when(productService.getAllProducts()).thenReturn(List.of());

        // Act
        List<Product> actualProducts = productController.getAllProducts();

        // Assert
        assertEquals(0, actualProducts.size());
    }
}