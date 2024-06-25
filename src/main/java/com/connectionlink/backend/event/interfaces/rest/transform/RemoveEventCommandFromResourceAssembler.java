package com.connectionlink.backend.event.interfaces.rest.transform;

import com.connectionlink.backend.event.domain.model.commands.RemoveUserCommand;
import com.connectionlink.backend.event.interfaces.rest.resources.RemoveUserResource;

public class RemoveEventCommandFromResourceAssembler {
    public static RemoveUserCommand toCommandFromResource(RemoveUserResource resource) {
        return new RemoveUserCommand(resource.username(), resource.eventId());
    }
}
