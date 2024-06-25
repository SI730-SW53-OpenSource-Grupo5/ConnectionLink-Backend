package com.connectionlink.backend.category.infrastructure.persitence.jpa;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);

    List<Category> findAll();
}
