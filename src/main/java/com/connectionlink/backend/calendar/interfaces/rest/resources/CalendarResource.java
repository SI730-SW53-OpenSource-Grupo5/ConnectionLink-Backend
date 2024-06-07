package com.connectionlink.backend.calendar.interfaces.rest.resources;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;

public record CalendarResource(Long id, Boolean available, Day day, Hour hour) {
}
