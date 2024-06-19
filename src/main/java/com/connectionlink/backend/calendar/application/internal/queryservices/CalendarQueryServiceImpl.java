package com.connectionlink.backend.calendar.application.internal.queryservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarsBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarsQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetCalendarByIdQuery;
import com.connectionlink.backend.calendar.domain.services.CalendarQueryService;
import com.connectionlink.backend.calendar.infraestructure.persistence.jpa.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarQueryServiceImpl implements CalendarQueryService {

    private final CalendarRepository calendarRepository;

    public CalendarQueryServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public Optional<Calendar> handle(GetCalendarByIdQuery query) {
        return this.calendarRepository.findById(query.id());
    }

    @Override
    public List<Calendar> handle(GetAllCalendarsBySpecialistUsernameQuery query) {
        return this.calendarRepository.findBySpecialistUsername(query.specialistUsername());
    }

    @Override
    public List<Calendar> handle(GetAllCalendarsQuery query) {
        return this.calendarRepository.findAll();
    }
}
