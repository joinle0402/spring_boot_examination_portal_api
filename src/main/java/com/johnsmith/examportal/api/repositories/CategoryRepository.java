package com.johnsmith.examportal.api.repositories;

import com.johnsmith.examportal.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Boolean existsByTitle(String title);
}
