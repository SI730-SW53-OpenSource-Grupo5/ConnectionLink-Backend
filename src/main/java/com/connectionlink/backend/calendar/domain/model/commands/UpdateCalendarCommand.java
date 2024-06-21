package com.connectionlink.backend.calendar.domain.model.commands;

public record UpdateCalendarCommand(Long HourId, Long DayId) {
    public UpdateCalendarCommand {
        if(HourId == null) {
            throw  new IllegalArgumentException("Hour Id cannot be null");
        }

        if(DayId == null) {
            throw  new IllegalArgumentException("Day Id cannot be null");
        }
    }
}
