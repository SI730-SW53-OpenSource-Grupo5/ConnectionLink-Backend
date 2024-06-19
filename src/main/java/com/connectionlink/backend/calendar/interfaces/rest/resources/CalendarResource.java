package com.connectionlink.backend.calendar.interfaces.rest.resources;

import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;

import java.util.Date;

public record CalendarResource(Long id, UserResource specialist, Date day, boolean isAvailable) {
}
