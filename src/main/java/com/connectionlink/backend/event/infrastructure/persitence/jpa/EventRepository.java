package com.connectionlink.backend.event.infrastructure.persitence.jpa;

import com.connectionlink.backend.event.domain.model.aggregates.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();

    List<Event> findByUsersId(Long userId);

    List<Event> findByCategoryId(Long categoryId);
    List<Event> findBySpecialistUsername(String specialistUsername);
    Optional<Event> findById(Long id);
    List<Event> findByUsersUsername(String username);
}
