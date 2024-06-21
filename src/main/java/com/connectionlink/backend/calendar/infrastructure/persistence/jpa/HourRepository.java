package com.connectionlink.backend.calendar.infrastructure.persistence.jpa;

import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HourRepository extends JpaRepository<Hour, Long> {
    List<Hour> findAll();
    Optional<Hour> findById(Long id);
    Optional<Hour> findByHour(String hour);

}
