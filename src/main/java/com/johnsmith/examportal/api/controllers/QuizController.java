package com.johnsmith.examportal.api.controllers;

import com.johnsmith.examportal.api.entities.Quiz;
import com.johnsmith.examportal.api.payloads.requests.UpdateActiveQuizRequest;
import com.johnsmith.examportal.api.services.interfaces.CategoryService;
import com.johnsmith.examportal.api.services.interfaces.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
public class QuizController {
    private final QuizService quizService;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.quizService.create(quiz));
    }

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.findAll(PageRequest.of(page - 1, limit)));
    }

    @GetMapping("/active")
    public ResponseEntity<?> findByActivatedQuizzes(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.quizService.findByActivatedQuizzes(PageRequest.of(page - 1, limit))
        );
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> findByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.quizService.findByCategory(this.categoryService.findById(categoryId), PageRequest.of(page - 1, limit))
        );
    }

    @GetMapping("/categories/{categoryId}/active")
    public ResponseEntity<?> findByActivatedQuizzesByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.quizService.findByActivatedQuizzesByCategory(this.categoryService.findById(categoryId), PageRequest.of(page - 1, limit))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.findById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> findBySlug(@PathVariable String slug) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.findBySlug(slug));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.update(quiz));
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<?> updateActive(@PathVariable Long id, @RequestBody UpdateActiveQuizRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.updateActive(id, request.getActive()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.delete(id));
    }

}
