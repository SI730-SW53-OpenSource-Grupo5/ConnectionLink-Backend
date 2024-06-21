package com.connectionlink.backend.calendar.domain.model.commands;

public record DeleteCalendaCommand(Long id) {
    public DeleteCalendaCommand {
        if(id == null) {
            throw  new IllegalArgumentException("calendar id cannot be null");
        }
    }
}
