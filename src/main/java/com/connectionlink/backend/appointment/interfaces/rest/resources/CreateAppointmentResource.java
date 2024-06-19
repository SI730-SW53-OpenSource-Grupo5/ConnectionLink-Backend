package com.connectionlink.backend.appointment.interfaces.rest.resources;

import java.util.Date;

public record CreateAppointmentResource(
        Date createdAt, String urlCall,
        String title, String description,
        String specialistUsername, String userUsername,
        Long calendarId) {
}
