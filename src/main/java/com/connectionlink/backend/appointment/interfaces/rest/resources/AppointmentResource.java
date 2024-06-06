package com.connectionlink.backend.appointment.interfaces.rest.resources;

import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;

import java.util.Date;

public record AppointmentResource(Long id, String title, String description, UserResource specialist, UserResource user, Date day) {
}
