package com.johnsmith.examportal.api.services.implementations;

import com.johnsmith.examportal.api.entities.Question;
import com.johnsmith.examportal.api.entities.Quiz;
import com.johnsmith.examportal.api.exceptions.ApiException;
import com.johnsmith.examportal.api.payloads.responses.PaginationResponse;
import com.johnsmith.examportal.api.payloads.responses.QuestionResponse;
import com.johnsmith.examportal.api.repositories.QuestionRepository;
import com.johnsmith.examportal.api.services.interfaces.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Override
    public Question create(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public PaginationResponse<QuestionResponse> findAll(Pageable pageable) {
        return convert(this.questionRepository.findAll(pageable));
    }

    @Override
    public PaginationResponse<QuestionResponse> findByQuiz(Quiz quiz, Pageable pageable) {
        return convert(this.questionRepository.findByQuiz(quiz, pageable));
    }

    public PaginationResponse<QuestionResponse> convert(Page<Question> page) {
        PaginationResponse<QuestionResponse> pagination = new PaginationResponse<>();
        List<QuestionResponse> content = page.getContent().stream()
                .map(question -> this.modelMapper.map(question, QuestionResponse.class)).toList();
        pagination.setContent(content);
        pagination.setCurrentPage(page.getNumber() + 1);
        pagination.setLimit(page.getSize());
        pagination.setTotalElements(page.getTotalElements());
        pagination.setTotalPages(page.getTotalPages());
        pagination.setLastPage(page.isLast());
        return pagination;
    }

    @Override
    public Question findById(Long id) {
        return this.questionRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Question not found with id: " + id));
    }

    @Override
    public Question findBySlug(String slug) {
        return this.questionRepository.findBySlug(slug)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Question not found with slug: " + slug));
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
}
