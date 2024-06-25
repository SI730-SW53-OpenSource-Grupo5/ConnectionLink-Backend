package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarAvailableCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.UpdateCalendarAvailableResource;

public class UpdateCalendarAvailableCommandFromResourceAssembler {
    public static UpdateCalendarAvailableCommand toCommandFromResource(UpdateCalendarAvailableResource resource) {
        return new UpdateCalendarAvailableCommand(resource.isAvailable());
    }
}
