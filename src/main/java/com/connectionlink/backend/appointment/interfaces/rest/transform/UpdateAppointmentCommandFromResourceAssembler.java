package com.connectionlink.backend.appointment.interfaces.rest.transform;

import com.connectionlink.backend.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.connectionlink.backend.appointment.interfaces.rest.resources.UpdateAppointmentResource;

public class UpdateAppointmentCommandFromResourceAssembler {

    public static UpdateAppointmentCommand toCommandFromResource(UpdateAppointmentResource resource) {
        return new UpdateAppointmentCommand(
                resource.userName(),
                resource.calendarId()
        );
    }
}
