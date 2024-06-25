package com.connectionlink.backend.iam.interfaces.rest.transform;

import com.connectionlink.backend.iam.domain.model.commands.UpdateUserCommand;
import com.connectionlink.backend.iam.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource) {
        return new UpdateUserCommand(resource.fullName(), resource.description(), resource.profileImageUrl(), resource.bannerImageUrl(), resource.age(), resource.birthday(), resource.cvUrl());
    }
}
