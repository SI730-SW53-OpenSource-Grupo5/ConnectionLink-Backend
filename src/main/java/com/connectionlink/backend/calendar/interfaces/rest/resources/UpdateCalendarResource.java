package com.connectionlink.backend.calendar.interfaces.rest.resources;

import com.connectionlink.backend.event.domain.model.queries.GetAllEventQuery;

import java.util.Date;

public record UpdateCalendarResource(Date day, GetAllEventQuery event) {
    public UpdateCalendarResource{
        if (day == null) {
            throw new IllegalArgumentException("day cannot be null");
        }
        if (event == null ) {
            throw new IllegalArgumentException("no event");
        }
    }
}
