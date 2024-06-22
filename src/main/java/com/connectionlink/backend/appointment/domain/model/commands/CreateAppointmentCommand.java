package com.connectionlink.backend.appointment.domain.model.commands;

import java.util.Date;

public record CreateAppointmentCommand(Date createdAt, Date updatedAt, String userUsername, Long calendarId) {

    public CreateAppointmentCommand {
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (updatedAt == null) {
            throw new IllegalArgumentException("updatedAt cannot be null");
        }
        if (userUsername == null || userUsername.isBlank()) {
            throw new IllegalArgumentException("userUsername cannot be null or empty");
        }
        if (calendarId == null) {
            throw new IllegalArgumentException("calendarId cannot be null");
        }
    }
}
