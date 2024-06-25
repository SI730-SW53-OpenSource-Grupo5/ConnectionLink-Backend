package com.connectionlink.backend.review.infrastructure.persistence.jpa;

import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.review.domain.model.aggregates.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAll();

    List<Review> findBySpecialistUsername(String specialistUsername);

    Optional<Review> findById(Long id);
}
