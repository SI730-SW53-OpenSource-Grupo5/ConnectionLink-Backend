package com.connectionlink.backend.calendar.domain.model.commands;

public record UpdateDayCommand(String day) {
    public UpdateDayCommand {
        if(day == null || day.isBlank()) {
            throw  new IllegalArgumentException("day cannot be null or empty");
        }
    }
}
