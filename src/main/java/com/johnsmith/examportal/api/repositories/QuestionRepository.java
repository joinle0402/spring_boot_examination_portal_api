package com.johnsmith.examportal.api.repositories;

import com.johnsmith.examportal.api.entities.Question;
import com.johnsmith.examportal.api.entities.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByQuiz(Quiz quiz, Pageable pageable);
    Optional<Question> findBySlug(String slug);
}
