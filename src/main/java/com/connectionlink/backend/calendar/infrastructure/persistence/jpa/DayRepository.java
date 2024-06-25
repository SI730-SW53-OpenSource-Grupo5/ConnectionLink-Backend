package com.connectionlink.backend.calendar.infrastructure.persistence.jpa;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {
    List<Day> findAll();
    Optional<Day> findById(Long id);
    Optional<Day> findByDay(String day);
}
