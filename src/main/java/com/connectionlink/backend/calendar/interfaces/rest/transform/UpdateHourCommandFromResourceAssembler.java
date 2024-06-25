package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.UpdateHourCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.UpdateHourResource;

public class UpdateHourCommandFromResourceAssembler {
    public static UpdateHourCommand toCommandFromResource(UpdateHourResource resource) {
        return new UpdateHourCommand(resource.hour());
    }
}
