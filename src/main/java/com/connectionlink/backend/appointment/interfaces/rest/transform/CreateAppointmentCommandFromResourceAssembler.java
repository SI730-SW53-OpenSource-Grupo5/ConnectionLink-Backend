package com.connectionlink.backend.appointment.interfaces.rest.transform;

import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;
import com.connectionlink.backend.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.connectionlink.backend.appointment.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {

    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
      return new CreateAppointmentCommand(
            resource.createdAt(),
            resource.updatedAt(),
            resource.userUsername(),
            resource.calendarId()
      );
    }
}
