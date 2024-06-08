package com.connectionlink.backend.authuser.interfaces.rest.transform;

import com.connectionlink.backend.authuser.domain.model.commands.CreateUserAuthCommand;
import com.connectionlink.backend.authuser.interfaces.rest.resources.CreateUserAuthResource;

public class CreateUserAuthCommandFromResourceAssembler {

    public static CreateUserAuthCommand toCommand(CreateUserAuthResource resource) {

        return new CreateUserAuthCommand(
            resource.firstName(),
            resource.lastName(),
            resource.phone(),
            resource.email(),
            resource.password(),
            resource.role()
        );
    }

}
