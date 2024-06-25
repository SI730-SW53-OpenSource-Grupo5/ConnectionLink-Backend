package com.connectionlink.backend.calendar.domain.model.commands;

public record UpdateCalendarAvailableCommand(Boolean isAvailable) {
    public UpdateCalendarAvailableCommand {
        if(isAvailable == null) {
            throw  new IllegalArgumentException("isAvailable cannot be null");
        }
    }
}
