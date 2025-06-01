package org.ruby.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.ruby.productservice.models.Product;
import org.ruby.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMockMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProductsAPI() throws Exception {
        //Arrange
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
        String expectedJson = objectMapper.writeValueAsString(expectedProducts);

        //Act
        when(productService.getAllProducts()).thenReturn(expectedProducts);
        //Assert
        mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/products"
                        )
                )
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }
}
