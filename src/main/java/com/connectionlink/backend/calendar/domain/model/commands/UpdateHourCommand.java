package com.connectionlink.backend.calendar.domain.model.commands;

public record UpdateHourCommand(String hour) {
    public UpdateHourCommand {
        if(hour == null || hour.isBlank()) {
            throw  new IllegalArgumentException("hour cannot be null or empty");
        }
    }
}
