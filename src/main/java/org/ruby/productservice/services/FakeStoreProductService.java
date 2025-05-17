package org.ruby.productservice.services;

import org.ruby.productservice.dtos.FakeStoreProductDto;
import org.ruby.productservice.exceptions.ProductNotFoundException;
import org.ruby.productservice.models.Category;
import org.ruby.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//Note: This service class will implement all the API's using FakeStore.
@Service(value = "fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;
    private final String FAKE_STORE_API_URL = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                restTemplate.getForEntity(
                        FAKE_STORE_API_URL + "/" + productId,
                        FakeStoreProductDto.class
                );
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        if (fakeStoreProductDto == null) {
            // If the product is not found, throw a custom exception
            throw new ProductNotFoundException(productId, "Product with id " + productId + " doesn't exist.");
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    @Override
    public List<Product> getAllProducts() {
        /*ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange(
                FAKE_STORE_API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                }
        );
        List<Map<String, Object>> fakeStoreProductDtos = responseEntity.getBody();
        return fakeStoreProductDtos.stream()
                .map(this::convertFakeStoreProductMapToProduct)
                .toList();*/
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(
                FAKE_STORE_API_URL,
                FakeStoreProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDtos = responseEntity.getBody();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }


    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

   /* private Product convertFakeStoreProductMapToProduct(Map fakeStoreProductMap) {
        if (fakeStoreProductMap == null) {
            return null;
        }
        Product product = new Product();
        product.setId(((Number) fakeStoreProductMap.get("id")).longValue());
        product.setTitle((String) fakeStoreProductMap.get("title"));
        product.setDescription((String) fakeStoreProductMap.get("description"));
        product.setPrice(((Number) fakeStoreProductMap.get("price")).doubleValue());
        product.setImageUrl((String) fakeStoreProductMap.get("image"));
        Category category = new Category();
        category.setTitle((String) fakeStoreProductMap.get("category"));
        product.setCategory(category);
        return product;

    }*/


}
