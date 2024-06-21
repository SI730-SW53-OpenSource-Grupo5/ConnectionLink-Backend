package com.connectionlink.backend.calendar.interfaces.rest.resources;

import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;

public record CalendarResource(Long id, Boolean isAvailable, DayResource day, HourResource hourResource, String url, UserResource user) {
}
