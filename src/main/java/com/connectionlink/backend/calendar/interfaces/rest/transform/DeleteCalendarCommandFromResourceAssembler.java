package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.DeleteCalendaCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.DeleteCalendarResource;

public class DeleteCalendarCommandFromResourceAssembler {
    public static DeleteCalendaCommand toCommandFromResource(DeleteCalendarResource resource) {
        return new DeleteCalendaCommand(resource.id());
    }
}
