package com.connectionlink.backend.calendar.domain.model.commands;

public record CreateCalendarCommand(Long dayId, Long hourId, String url, String specialistUsername){
    public CreateCalendarCommand {
        if(dayId == null ) {
            throw  new IllegalArgumentException("day Id cannot be null");
        }

        if(hourId == null) {
            throw  new IllegalArgumentException("hour Id  cannot be null");
        }

        if(url == null || url.isBlank()) {
            throw  new IllegalArgumentException("url cannot be null or empty");
        }

        if(specialistUsername == null || specialistUsername.isBlank()) {
            throw  new IllegalArgumentException("specialist Username cannot be null or empty");
        }
    }
}
