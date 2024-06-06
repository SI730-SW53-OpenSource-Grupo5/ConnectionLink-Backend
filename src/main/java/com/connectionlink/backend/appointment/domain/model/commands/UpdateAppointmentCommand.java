package com.connectionlink.backend.appointment.domain.model.commands;

import java.util.Date;

public record UpdateAppointmentCommand(String title, String description, String usernameSpecialist, String usernameUser, Date day) {

    public UpdateAppointmentCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (usernameSpecialist == null || usernameSpecialist.isBlank()) {
            throw new IllegalArgumentException("usernameSpecialist cannot be null or empty");
        }
        if (usernameUser == null || usernameUser.isBlank()) {
            throw new IllegalArgumentException("usernameUser cannot be null or empty");
        }
        if (day == null) {
            throw new IllegalArgumentException("day cannot be null");
        }
    }
}
