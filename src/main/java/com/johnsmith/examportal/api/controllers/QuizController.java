package com.johnsmith.examportal.api.controllers;

import com.johnsmith.examportal.api.entities.Quiz;
import com.johnsmith.examportal.api.services.interfaces.QuizService;
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
@RequestMapping("/api/v1/quizzes")
public class QuizController {
    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.quizService.create(quiz));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.findById(id));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.update(quiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.quizService.delete(id));
    }

}
