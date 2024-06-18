package com.connectionlink.backend.calendar.interfaces.rest.trasform;

import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.category.domain.model.commands.CreateCategoryCommand;


public class CreateCalendarCommandFromResourceAssembler {
    public static CreateCalendarCommand toCommandFromResource(CreateCalendarCommand resource){
        return new CreateCalendarCommand(resource.day(), resource.event());
    }
}
