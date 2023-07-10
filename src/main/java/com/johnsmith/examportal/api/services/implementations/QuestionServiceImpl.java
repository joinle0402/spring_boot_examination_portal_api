package com.johnsmith.examportal.api.services.implementations;

import com.johnsmith.examportal.api.entities.Question;
import com.johnsmith.examportal.api.entities.Quiz;
import com.johnsmith.examportal.api.exceptions.ApiException;
import com.johnsmith.examportal.api.repositories.QuestionRepository;
import com.johnsmith.examportal.api.services.interfaces.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Question create(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Question findById(Long id) {
        return this.questionRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Question not found with id: " + id));
    }

    @Override
    public Question update(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public String delete(Long id) {
        Question questionToDelete = this.findById(id);
        this.questionRepository.delete(questionToDelete);
        return "Deleted question!";
    }

    @Override
    public List<Question> findByQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }
}
