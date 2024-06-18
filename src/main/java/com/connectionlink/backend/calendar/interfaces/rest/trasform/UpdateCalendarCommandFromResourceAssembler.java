package com.connectionlink.backend.calendar.interfaces.rest.trasform;

import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarCommand;

public class UpdateCalendarCommandFromResourceAssembler {
    public static UpdateCalendarCommand toCommandFromResource(UpdateCalendarCommand resource){
        return new UpdateCalendarCommand(resource.day(), resource.event());
    }
}
