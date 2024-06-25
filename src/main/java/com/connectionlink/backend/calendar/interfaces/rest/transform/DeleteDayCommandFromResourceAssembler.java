package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.commands.DeleteDayCommand;
import com.connectionlink.backend.calendar.interfaces.rest.resources.DeleteDayResource;

public class DeleteDayCommandFromResourceAssembler {
    public static DeleteDayCommand toCommandFromResource(DeleteDayResource resource) {
        return new DeleteDayCommand(resource.id());
    }
}
