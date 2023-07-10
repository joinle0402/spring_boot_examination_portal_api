package com.johnsmith.examportal.api.services.implementations;

import com.johnsmith.examportal.api.entities.Quiz;
import com.johnsmith.examportal.api.exceptions.ApiException;
import com.johnsmith.examportal.api.repositories.QuizRepository;
import com.johnsmith.examportal.api.services.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Override
    public Quiz create(Quiz quiz) {
        try {
            return this.quizRepository.save(quiz);
        } catch (Exception exception) {
            if (exception.getMessage().contains("Unique")) {
                throw new ApiException(HttpStatus.CONFLICT, "Quiz title already exists!");
            }
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public List<Quiz> findAll() {
        return this.quizRepository.findAll();
    }

    @Override
    public Quiz findById(Long id) {
        return this.quizRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Quiz not found with id: " + id));
    }

    @Override
    public Quiz update(Quiz quiz) {
        try {
            return this.quizRepository.save(quiz);
        } catch (Exception exception) {
            if (exception.getMessage().contains("Unique")) {
                throw new ApiException(HttpStatus.CONFLICT, "Quiz title already exists!");
            }
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public String delete(Long id) {
        Quiz quizToDelete = this.findById(id);
        this.quizRepository.delete(quizToDelete);
        return "Deleted quiz!";
    }
}
