package com.connectionlink.backend.calendar.application.internal.queryservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarAvailableBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetCalendarByIdQuery;
import com.connectionlink.backend.calendar.domain.services.CalendarQueryService;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.CalendarRepository;
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
    public List<Calendar> handle(GetAllCalendarQuery query) {
        return this.calendarRepository.findAll();
    }

    @Override
    public List<Calendar> handle(GetAllCalendarBySpecialistUsernameQuery query) {
        return this.calendarRepository.findBySpecialistUsername(query.specialistUsername());
    }

    @Override
    public List<Calendar> handle(GetAllCalendarAvailableBySpecialistUsernameQuery query) {
        return this.calendarRepository.findBySpecialistUsernameAndIsAvailableTrue(query.specialistUsername());
    }

    @Override
    public Optional<Calendar> handle(GetCalendarByIdQuery query) {
        return this.calendarRepository.findById(query.id());
    }
}
