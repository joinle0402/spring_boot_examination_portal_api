package com.johnsmith.examportal.api.services.interfaces;

import com.johnsmith.examportal.api.entities.Category;
import com.johnsmith.examportal.api.entities.Quiz;
import com.johnsmith.examportal.api.payloads.responses.PaginationResponse;
import org.springframework.data.domain.Pageable;

public interface QuizService {
    Quiz create(Quiz quiz);

    PaginationResponse<Quiz> findAll(Pageable pageable);
    PaginationResponse<Quiz> findByActivatedQuizzes(Pageable pageable);
    PaginationResponse<Quiz> findByActivatedQuizzesByCategory(Category category, Pageable pageable);

    PaginationResponse<Quiz> findByCategory(Category category, Pageable pageable);

    Quiz findById(Long id);

    Quiz findBySlug(String slug);

    Quiz update(Quiz quiz);
    Quiz updateActive(Long id, Boolean active);

    String delete(Long id);
}
