package com.johnsmith.examportal.api.services.interfaces;

import com.johnsmith.examportal.api.entities.Question;
import com.johnsmith.examportal.api.entities.Quiz;
import com.johnsmith.examportal.api.payloads.responses.PaginationResponse;
import com.johnsmith.examportal.api.payloads.responses.QuestionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {
    Question create(Question question);
    PaginationResponse<QuestionResponse> findAll(Pageable pageable);
    PaginationResponse<QuestionResponse> findByQuiz(Quiz quiz, Pageable pageable);
    Question findById(Long id);
    Question findBySlug(String slug);
    Question update(Question question);
    String delete(Long id);
}
