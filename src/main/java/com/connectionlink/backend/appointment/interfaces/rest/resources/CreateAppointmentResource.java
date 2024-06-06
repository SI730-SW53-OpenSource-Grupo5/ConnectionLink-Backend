package com.connectionlink.backend.appointment.interfaces.rest.resources;

import java.util.Date;

public record CreateAppointmentResource(String title, String description, String usernameSpecialist, String usernameUser, Date day) {
    public CreateAppointmentResource {
        if (title == null ) {
            throw new IllegalArgumentException("title cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (usernameSpecialist == null ) {
            throw new IllegalArgumentException("usernameSpecialist cannot be null");
        }
        if (usernameUser == null) {
            throw new IllegalArgumentException("usernameUser cannot be null");
        }
        if (day == null ) {
            throw new IllegalArgumentException("day cannot be null");
        }
    }
}
