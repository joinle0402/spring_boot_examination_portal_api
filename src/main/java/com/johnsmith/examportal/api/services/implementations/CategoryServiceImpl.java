package com.johnsmith.examportal.api.services.implementations;

import com.johnsmith.examportal.api.entities.Category;
import com.johnsmith.examportal.api.exceptions.ApiException;
import com.johnsmith.examportal.api.repositories.CategoryRepository;
import com.johnsmith.examportal.api.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        if (this.categoryRepository.existsByTitle(category.getTitle())) {
            throw new ApiException(HttpStatus.CONFLICT, "Category already exists!");
        }
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Category not found with id: " + id));
    }

    @Override
    public Category update(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public String delete(Integer id) {
        Category category = this.findById(id);
        this.categoryRepository.delete(category);
        return "Deleted category!";
    }
}
