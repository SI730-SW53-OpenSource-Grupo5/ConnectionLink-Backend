package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CreateCalendarResource;

public class CreateCalendarCommandFromResourceAssembler {
    public static CreateCalendarCommand toCommandFromResource(CreateCalendarResource resource) {
        return new CreateCalendarCommand(resource.specialistUsername(), resource.day(), resource.isAvailable());
    }
}
