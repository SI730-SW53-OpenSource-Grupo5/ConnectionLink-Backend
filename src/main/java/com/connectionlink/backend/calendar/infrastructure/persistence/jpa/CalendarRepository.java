package com.connectionlink.backend.calendar.infrastructure.persistence.jpa;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAll();

    List<Calendar> findBySpecialistUsername(String specialistUsername);

    List<Calendar> findBySpecialistUsernameAndIsAvailableTrue(String specialistUsername);

    Optional<Calendar> findById(Long id);

    Optional<Calendar> findByHourIdAndId(Long hourId, Long calendarId);
}
