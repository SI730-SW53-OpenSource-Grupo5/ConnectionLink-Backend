package com.connectionlink.backend.appointment.interfaces.rest.resources;

import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;

import java.util.Date;

public record AppointmentResource(Long id, Date createdAt, Date updatedAt, UserResource user, CalendarResource calendar) {
}
