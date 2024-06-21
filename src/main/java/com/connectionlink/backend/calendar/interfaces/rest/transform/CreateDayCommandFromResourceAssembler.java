package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.CreateDayCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CreateDayResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CreateHourResource;

public class CreateDayCommandFromResourceAssembler {
    public static CreateDayCommand toCommandFromResource(CreateDayResource resource) {
        return new CreateDayCommand(resource.day());
    }
}
