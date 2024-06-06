package com.connectionlink.backend.appointment.interfaces.rest.transform;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.interfaces.rest.resources.AppointmentResource;
import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.user.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class AppointmentResourceFromEntityAssembler {

    public static AppointmentResource toResourceFromEntity(Appointment entity) {
        UserResource specialist = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getSpecialist());
        UserResource user = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser());

        return new AppointmentResource(entity.getId(), entity.getTitle(), entity.getDescription(), specialist, user, entity.getDay());
    }
}
