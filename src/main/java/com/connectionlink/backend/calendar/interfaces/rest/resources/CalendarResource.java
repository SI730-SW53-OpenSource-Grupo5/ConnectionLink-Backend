package com.connectionlink.backend.calendar.interfaces.rest.resources;

import com.connectionlink.backend.event.domain.model.aggregates.Event;

import java.util.Date;

public record CalendarResource(Long id, Date day, Event event) {
}
