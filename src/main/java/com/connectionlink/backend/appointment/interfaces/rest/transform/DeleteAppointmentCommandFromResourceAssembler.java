package com.connectionlink.backend.appointment.interfaces.rest.transform;

import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;
import com.connectionlink.backend.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.connectionlink.backend.appointment.interfaces.rest.resources.DeleteAppointmentResource;

public class DeleteAppointmentCommandFromResourceAssembler {

    public static DeleteAppointmentCommand toCommandFromResource(DeleteAppointmentResource resource) {
        return new DeleteAppointmentCommand(resource.id());
    }
}
