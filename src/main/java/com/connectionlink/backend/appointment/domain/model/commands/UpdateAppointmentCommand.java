package com.connectionlink.backend.appointment.domain.model.commands;

public record UpdateAppointmentCommand(String userName, Long calendarId) {

    public UpdateAppointmentCommand {
        if(userName == null) {
            throw  new IllegalArgumentException("User name cannot be null");
        }

        if(calendarId == null) {
            throw  new IllegalArgumentException("Calendar Id cannot be null");
        }
    }
}
