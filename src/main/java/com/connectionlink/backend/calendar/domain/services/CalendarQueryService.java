package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.queries.*;

import java.util.List;
public interface CalendarQueryService {

    List<Calendar> handle(GetCalendarQuery query);
}
