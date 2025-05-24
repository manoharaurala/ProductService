package org.ruby.productservice.services;

import org.ruby.productservice.models.Category;
import org.ruby.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;
    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public boolean deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional=categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            categoryRepository.delete(category);
            return true;
        } else {
            return false;
        }
    }
}
