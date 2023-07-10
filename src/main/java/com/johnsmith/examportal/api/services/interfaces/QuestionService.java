package com.johnsmith.examportal.api.services.interfaces;

import com.johnsmith.examportal.api.entities.Question;
import com.johnsmith.examportal.api.entities.Quiz;

import java.util.List;

public interface QuestionService {
    Question create(Question question);
    List<Question> findAll();
    Question findById(Long id);
    Question update(Question question);
    String delete(Long id);
    List<Question> findByQuiz(Quiz quiz);
}
