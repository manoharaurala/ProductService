package org.ruby.productservice.services;

import org.mockito.Mock;
import org.ruby.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SelfCategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;

}