package com.connectionlink.backend.event.interfaces.rest.transform;

import com.connectionlink.backend.event.domain.model.commands.AddUserCommand;
import com.connectionlink.backend.event.interfaces.rest.resources.AddUserResource;

public class AddUserCommandFromResourceAssembler {
    public static AddUserCommand toCommandFromResource(AddUserResource resource) {
        return new AddUserCommand(resource.username(), resource.eventId());
    }
}
