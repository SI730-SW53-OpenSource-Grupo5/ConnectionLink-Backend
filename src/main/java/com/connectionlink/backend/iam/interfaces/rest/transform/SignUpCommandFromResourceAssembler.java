package com.connectionlink.backend.iam.interfaces.rest.transform;

import com.connectionlink.backend.iam.domain.model.commands.SignUpCommand;
import com.connectionlink.backend.iam.domain.model.entities.Role;
import com.connectionlink.backend.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(Role::toRoleFromName).toList() : new ArrayList<Role>();
        return new SignUpCommand(resource.fullName(), resource.username(), resource.description(), resource.profileImageUrl(), resource.bannerImageUrl(), resource.email(), resource.password(), resource.age(), resource.birthday(), resource.isSpecialistUser(), resource.cvUrl(), roles);
    }
}
