package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.DeleteHourCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.DeleteHourResource;

public class DeleteHourComamndFromResourceAssembler {
    public static DeleteHourCommand toCommandFromResource(DeleteHourResource resource) {
        return new DeleteHourCommand(resource.id());
    }
}
