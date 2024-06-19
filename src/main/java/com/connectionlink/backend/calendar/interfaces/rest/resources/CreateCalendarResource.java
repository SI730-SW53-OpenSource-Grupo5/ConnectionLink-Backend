package com.connectionlink.backend.calendar.interfaces.rest.resources;

import java.util.Date;

public record CreateCalendarResource(String specialistUsername, Date day, boolean isAvailable) {
    public CreateCalendarResource {
        if (specialistUsername == null) {
            throw new IllegalArgumentException("specialistUsername cannot be null");
        }
        if (day == null) {
            throw new IllegalArgumentException("day cannot be null");
        }
    }
}
