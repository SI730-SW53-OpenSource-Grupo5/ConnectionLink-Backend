package com.connectionlink.backend.calendar.domain.services;


import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarsBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarsQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetCalendarByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CalendarQueryService {

    Optional<Calendar> handle(GetCalendarByIdQuery query);

    List<Calendar> handle(GetAllCalendarsBySpecialistUsernameQuery query);

    List<Calendar> handle(GetAllCalendarsQuery query);
}
