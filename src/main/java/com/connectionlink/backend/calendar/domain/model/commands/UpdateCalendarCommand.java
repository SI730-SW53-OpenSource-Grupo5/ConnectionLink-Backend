package com.connectionlink.backend.calendar.domain.model.commands;

import com.connectionlink.backend.event.domain.model.queries.GetAllEventQuery;

import java.util.Date;

public record UpdateCalendarCommand(Date day, GetAllEventQuery event) {
    public UpdateCalendarCommand {
        if (day == null) {
            throw new IllegalArgumentException("day cannot be null");
        }
        if (event == null ) {
            throw new IllegalArgumentException("no event");
        }
    }
}
