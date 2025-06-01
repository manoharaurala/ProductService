package org.ruby.productservice.controllers;

import org.ruby.productservice.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        boolean isDeleted = categoryService.deleteCategory(categoryId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
