package com.connectionlink.backend.calendar.domain.model.commands;

public record DeleteDayCommand(Long id) {
    public DeleteDayCommand {
        if(id == null) {
            throw  new IllegalArgumentException("day id cannot be null");
        }
    }
}
