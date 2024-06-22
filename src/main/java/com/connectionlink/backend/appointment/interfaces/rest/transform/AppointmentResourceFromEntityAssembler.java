package com.connectionlink.backend.appointment.interfaces.rest.transform;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.interfaces.rest.resources.AppointmentResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;
import com.connectionlink.backend.calendar.interfaces.rest.transform.CalendarResourceFromEntityAssembler;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class AppointmentResourceFromEntityAssembler {

    public static AppointmentResource toResourceFromEntity(Appointment entity) {
        UserResource user = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser());

        CalendarResource calendar = CalendarResourceFromEntityAssembler.toResourceFromEntity(entity.getCalendar());

        return new AppointmentResource(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                user,
                calendar);
    }
}
