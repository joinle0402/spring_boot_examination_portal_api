package com.johnsmith.examportal.api.repositories;

import com.johnsmith.examportal.api.entities.Category;
import com.johnsmith.examportal.api.entities.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Page<Quiz> findByCategory(Category category, Pageable pageable);
    Page<Quiz> findByActive(Boolean active, Pageable pageable);
    Page<Quiz> findByCategoryAndActive(Category category, Boolean active, Pageable pageable);
    Optional<Quiz> findBySlug(String slug);
}
