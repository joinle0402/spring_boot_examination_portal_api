package com.johnsmith.examportal.api.controllers;

import com.johnsmith.examportal.api.entities.Category;
import com.johnsmith.examportal.api.services.interfaces.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.create(category));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.findById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> findBySlug(@PathVariable String slug) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.findBySlug(slug));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.delete(id));
    }
}
