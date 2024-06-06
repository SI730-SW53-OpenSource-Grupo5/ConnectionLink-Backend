package com.connectionlink.backend.appointment.interfaces.rest.resources;

import java.util.Date;

public record UpdateAppointmentResource(Long id, String title, String description, String usernameSpecialist, String usernameUser, Date day) {

    public UpdateAppointmentResource {
        if (title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        if (description == null ) {
            throw new IllegalArgumentException("description cannot be null");
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
