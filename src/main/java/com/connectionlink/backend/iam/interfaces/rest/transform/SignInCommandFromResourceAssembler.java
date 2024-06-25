package com.connectionlink.backend.iam.interfaces.rest.transform;


import com.connectionlink.backend.iam.domain.model.commands.SignInCommand;
import com.connectionlink.backend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
