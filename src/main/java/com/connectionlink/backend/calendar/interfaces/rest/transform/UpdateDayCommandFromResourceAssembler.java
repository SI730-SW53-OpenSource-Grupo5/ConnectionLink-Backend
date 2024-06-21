package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.UpdateDayCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.UpdateDayResource;

public class UpdateDayCommandFromResourceAssembler {
    public static UpdateDayCommand toCommandFromResource(UpdateDayResource resource) {
        return new UpdateDayCommand(resource.day());
    }
}
