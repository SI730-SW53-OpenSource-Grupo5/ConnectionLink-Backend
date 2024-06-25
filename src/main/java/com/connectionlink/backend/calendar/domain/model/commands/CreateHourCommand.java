package com.connectionlink.backend.calendar.domain.model.commands;

public record CreateHourCommand(String hour) {
    public CreateHourCommand {
        if(hour == null || hour.isBlank()) {
            throw  new IllegalArgumentException("hour cannot be null or empty");
        }
    }
}
