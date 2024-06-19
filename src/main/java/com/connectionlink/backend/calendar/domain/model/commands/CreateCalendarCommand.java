package com.connectionlink.backend.calendar.domain.model.commands;

import java.util.Date;


public record CreateCalendarCommand(String username, Date day, boolean isAvailable) {
    public CreateCalendarCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be null or empty");
        }
        if (day == null) {
            throw new IllegalArgumentException("day cannot be null");
        }
    }
}
