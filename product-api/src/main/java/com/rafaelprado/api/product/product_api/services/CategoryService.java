package com.rafaelprado.api.product.product_api.services;

import com.rafaelprado.api.product.product_api.model.Category;
import com.rafaelprado.api.product.product_api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Page<Category> getAllCategoriesPageable(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(String id, Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            updatedCategory.setId(id); // Garante que o ID seja mantido
            return categoryRepository.save(updatedCategory);
        }
        throw new RuntimeException("Category not found with id: " + id);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
