package com.connectionlink.backend.calendar.domain.model.commands;

public record CreateDayCommand(String day) {
    public CreateDayCommand {
        if(day == null || day.isBlank()) {
            throw  new IllegalArgumentException("day cannot be null or empty");
        }
    }
}
