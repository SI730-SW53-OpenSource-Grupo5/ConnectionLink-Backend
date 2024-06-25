package com.connectionlink.backend.appointment.interfaces.rest.resources;

import java.util.Date;

public record CreateAppointmentResource(
        Date createdAt,
        Date updatedAt,
        String userUsername,
        Long calendarId) {
}
