package com.johnsmith.examportal.api.controllers;

import com.johnsmith.examportal.api.entities.Question;
import com.johnsmith.examportal.api.payloads.responses.QuestionResponse;
import com.johnsmith.examportal.api.services.interfaces.QuestionService;
import com.johnsmith.examportal.api.services.interfaces.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/v1/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final QuizService quizService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.modelMapper.map(this.questionService.create(question), QuestionResponse.class)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.questionService.findAll(PageRequest.of(page - 1, limit)));
    }

    @GetMapping("/quizzes/{quizId}")
    public ResponseEntity<?> findByQuiz(
            @PathVariable Long quizId,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.questionService.findByQuiz(this.quizService.findById(quizId), PageRequest.of(page - 1, limit))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.modelMapper.map(this.questionService.findById(id), QuestionResponse.class)
        );
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> findBySlug(@PathVariable String slug) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.modelMapper.map(this.questionService.findBySlug(slug), QuestionResponse.class)
        );
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.modelMapper.map(this.questionService.update(question), QuestionResponse.class)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.questionService.delete(id));
    }
}
