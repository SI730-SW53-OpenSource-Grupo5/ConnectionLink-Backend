package com.connectionlink.backend.appointment.domain.model.commands;

import java.util.Date;

public record CreateAppointmentCommand(Date createdAt, String urlCall, String title, String description, String specialistUsername, String userUsername, Long calendarId) {

    public CreateAppointmentCommand {
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (urlCall == null || urlCall.isBlank()) {
            throw new IllegalArgumentException("urlCall cannot be null or empty");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (specialistUsername == null || specialistUsername.isBlank()) {
            throw new IllegalArgumentException("specialistUsername cannot be null or empty");
        }
        if (userUsername == null || userUsername.isBlank()) {
            throw new IllegalArgumentException("userUsername cannot be null or empty");
        }
        if (calendarId == null) {
            throw new IllegalArgumentException("calendarId cannot be null");
        }
    }
}
