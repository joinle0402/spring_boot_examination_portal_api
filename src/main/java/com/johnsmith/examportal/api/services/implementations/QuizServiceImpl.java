package com.johnsmith.examportal.api.services.implementations;

import com.johnsmith.examportal.api.entities.Category;
import com.johnsmith.examportal.api.entities.Quiz;
import com.johnsmith.examportal.api.exceptions.ApiException;
import com.johnsmith.examportal.api.payloads.responses.PaginationResponse;
import com.johnsmith.examportal.api.repositories.QuizRepository;
import com.johnsmith.examportal.api.services.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public PaginationResponse<Quiz> findAll(Pageable pageable) {
        return convert(this.quizRepository.findAll(pageable));
    }

    @Override
    public PaginationResponse<Quiz> findByActivatedQuizzes(Pageable pageable) {
        return convert(this.quizRepository.findByActive(true, pageable));
    }

    @Override
    public PaginationResponse<Quiz> findByActivatedQuizzesByCategory(Category category, Pageable pageable) {
        return convert(this.quizRepository.findByCategoryAndActive(category, true, pageable));
    }

    @Override
    public PaginationResponse<Quiz> findByCategory(Category category, Pageable pageable) {
        return convert(this.quizRepository.findByCategory(category, pageable));
    }

    public PaginationResponse<Quiz> convert(Page<Quiz> page) {
        PaginationResponse<Quiz> pagination = new PaginationResponse<>();
        pagination.setContent(page.getContent());
        pagination.setCurrentPage(page.getNumber() + 1);
        pagination.setLimit(page.getSize());
        pagination.setTotalElements(page.getTotalElements());
        pagination.setTotalPages(page.getTotalPages());
        pagination.setLastPage(page.isLast());
        return pagination;
    }

    @Override
    public Quiz findById(Long id) {
        return this.quizRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Quiz not found with id: " + id));
    }

    @Override
    public Quiz findBySlug(String slug) {
        return this.quizRepository.findBySlug(slug)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Quiz not found with slug: " + slug));
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
    public Quiz updateActive(Long id, Boolean active) {
        Quiz quizToUpdateActive = this.findById(id);
        quizToUpdateActive.setActive(active);
        this.quizRepository.save(quizToUpdateActive);
        return quizToUpdateActive;
    }

    @Override
    public String delete(Long id) {
        Quiz quizToDelete = this.findById(id);
        this.quizRepository.delete(quizToDelete);
        return "Deleted quiz!";
    }
}
