package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.UpdateCalendarResource;

public class UpdateCalendarCommandFromResourceAssembler {
    public static UpdateCalendarCommand toCommandFromResource(UpdateCalendarResource resource) {
        return new UpdateCalendarCommand(resource.HourId(), resource.DayId());
    }
}
