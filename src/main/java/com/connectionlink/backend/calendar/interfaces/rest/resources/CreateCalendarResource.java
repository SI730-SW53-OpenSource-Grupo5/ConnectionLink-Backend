package com.connectionlink.backend.calendar.interfaces.rest.resources;

public record CreateCalendarResource(Long dayId, Long hourId, String url, String specialistUsername) {
}
