package com.connectionlink.backend.calendar.domain.model.commands;

public record DeleteHourCommand(Long id) {
    public DeleteHourCommand {
        if(id == null) {
            throw  new IllegalArgumentException("hour id cannot be null");
        }
    }
}
