package org.ruby.productservice.services;

import org.ruby.productservice.dtos.FakeStoreProductDto;
import org.ruby.productservice.exceptions.ProductNotFoundException;
import org.ruby.productservice.models.Category;
import org.ruby.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


//Note: This service class will implement all the API's using FakeStore.
@Service(value = "fakeStoreProductService")
//@Primary
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;
    private final String FAKE_STORE_API_URL = "https://fakestoreapi.com/products";
    private final RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        //First check if the product with the given id exists in the Redis cache.
        //If it exists, return the product from the cache.
        Product product = (Product) redisTemplate.opsForHash().get("products", "product_" + productId.toString());
        if (product != null) {
            return product;
        }

        //If it doesn't exist, call the FakeStore API to get the product details.
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
        //Before returning the product, save it in the Redis cache.
        product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        redisTemplate.opsForHash().put("products", "product_" + productId, product);
        return product;
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
        //Check if the products are already cached in Redis.
//        List<Object> cachedProducts = (List<Object>)redisTemplate.opsForHash().values("products");
//        if (cachedProducts != null && !cachedProducts.isEmpty()) {
//            // If products are cached, return them
//            return cachedProducts.stream()
//                    .map(product -> (Product) product)
//                    .toList();
//        }

        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(
                FAKE_STORE_API_URL,
                FakeStoreProductDto[].class
        );

        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDtos = responseEntity.getBody();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        // Save the products in Redis cache
//        for (Product product : products) {
//            redisTemplate.opsForHash().put("products", "product_" + product.getId().toString(), product);
//        }
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

    @Override
    public Page<Product> getProductByTitle(String title, int pageNumber, int pageSize) {
        return Page.empty();
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
        product.setImgUrl(fakeStoreProductDto.getImage());
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
