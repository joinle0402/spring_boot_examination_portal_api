package com.johnsmith.examportal.api.services.interfaces;

import com.johnsmith.examportal.api.entities.Quiz;

import java.util.List;

public interface QuizService {
    Quiz create(Quiz quiz);
    List<Quiz> findAll();
    Quiz findById(Long id);
    Quiz update(Quiz quiz);
    String delete(Long id);
}
