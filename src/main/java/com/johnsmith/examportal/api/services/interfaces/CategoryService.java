package com.johnsmith.examportal.api.services.interfaces;

import com.johnsmith.examportal.api.entities.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> findAll();
    Category findById(Integer id);
    Category update(Category category);
    String delete(Integer id);
}
