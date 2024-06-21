package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.CreateHourCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CreateHourResource;

public class CreateHourCommandFromResourceAssembler {
    public static CreateHourCommand  toCommandFromResource(CreateHourResource resource) {
        return new CreateHourCommand(resource.hour());
    }
}
