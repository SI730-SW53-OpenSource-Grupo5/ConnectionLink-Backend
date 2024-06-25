package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarAvailableBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetCalendarByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CalendarQueryService {
    List<Calendar> handle(GetAllCalendarQuery query);

    List<Calendar> handle(GetAllCalendarBySpecialistUsernameQuery query);

    List<Calendar> handle(GetAllCalendarAvailableBySpecialistUsernameQuery query);

    Optional<Calendar> handle(GetCalendarByIdQuery query);
}
