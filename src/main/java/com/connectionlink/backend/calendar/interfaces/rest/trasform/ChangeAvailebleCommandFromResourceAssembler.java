package com.connectionlink.backend.calendar.interfaces.rest.trasform;

import com.connectionlink.backend.calendar.domain.model.commands.ChangeAvailable;
import com.connectionlink.backend.calendar.interfaces.rest.resources.ChangeAvailableResource;

public class ChangeAvailebleCommandFromResourceAssembler {
    public static ChangeAvailable toCommandFromResource(ChangeAvailableResource resource) {
        return new ChangeAvailable(resource.available());
    }
}
