package com.connectionlink.backend.calendar.intraestructure.persitence.jpa;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface CalendarRepository extends JpaRepository<Calendar, Long>{

    List<Calendar> finAll();
}
