package com.connectionlink.backend.event.interfaces.rest.transform;

import com.connectionlink.backend.event.domain.model.commands.CreateEventCommand;
import com.connectionlink.backend.event.interfaces.rest.resources.CreateEventResource;

public class CreateEventComamndFromResourceAssembler {
    public static CreateEventCommand toCommandFromResource(CreateEventResource resource) {
        return new CreateEventCommand(resource.title(), resource.description(), resource.profileImageUrl(), resource.bannerImageUrl(), resource.day(), resource.categoryId(), resource.username());
    }

}
