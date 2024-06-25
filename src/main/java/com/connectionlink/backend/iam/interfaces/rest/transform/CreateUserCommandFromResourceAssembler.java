package com.connectionlink.backend.iam.interfaces.rest.transform;

import com.connectionlink.backend.iam.domain.model.commands.CreateUserCommand;
import com.connectionlink.backend.iam.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(resource.fullName(),resource.username(), resource.description(), resource.profileImageUrl(), resource.bannerImageUrl(), resource.email(), resource.password(), resource.age(), resource.birthday(), resource.isSpecialistUser(), resource.cvUrl());
    }
}
