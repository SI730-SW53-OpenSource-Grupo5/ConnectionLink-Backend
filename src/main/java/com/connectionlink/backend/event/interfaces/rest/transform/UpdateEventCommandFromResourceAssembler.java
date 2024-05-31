package com.connectionlink.backend.event.interfaces.rest.transform;

import com.connectionlink.backend.event.domain.model.commands.UpdateEventCommand;
import com.connectionlink.backend.event.interfaces.rest.resources.UpdateEventResource;

public record UpdateEventCommandFromResourceAssembler() {
    public static UpdateEventCommand toCommandFromResource(UpdateEventResource resource) {
        return new UpdateEventCommand(resource.title(), resource.description(), resource.profileImageUrl(), resource.bannerImageUrl(), resource.day(), resource.categoryId(), resource.username());
    }
}
