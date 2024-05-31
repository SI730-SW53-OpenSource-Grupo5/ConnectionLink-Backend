package com.connectionlink.backend.user.interfaces.rest.transform;

import com.connectionlink.backend.user.domain.model.commands.UpdateUserCommand;
import com.connectionlink.backend.user.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource) {
        return new UpdateUserCommand(resource.fullName(), resource.description(), resource.profileImageUrl(), resource.bannerImageUrl(), resource.age(), resource.birthday(), resource.cvUrl());
    }
}
