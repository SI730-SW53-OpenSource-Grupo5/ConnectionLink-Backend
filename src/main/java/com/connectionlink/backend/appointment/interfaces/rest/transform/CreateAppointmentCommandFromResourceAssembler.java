package com.connectionlink.backend.appointment.interfaces.rest.transform;

import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;
import com.connectionlink.backend.appointment.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {

    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
      return new CreateAppointmentCommand(
            resource.createdAt(),
            resource.urlCall(),
            resource.title(),
            resource.description(),
            resource.specialistUsername(),
            resource.userUsername(),
            resource.calendarId()
      );
    }
}
