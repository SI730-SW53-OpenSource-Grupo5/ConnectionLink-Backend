package com.connectionlink.backend.iam.interfaces.rest;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.domain.services.UserCommandService;
import com.connectionlink.backend.iam.domain.services.UserQueryService;
import com.connectionlink.backend.iam.interfaces.rest.resources.AuthenticationUserResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.SignInResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.SignUpResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.connectionlink.backend.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.connectionlink.backend.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.connectionlink.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.connectionlink.shared.infrastructure.documentation.openapi.configuration.OpenApiConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/authentication")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationUserResource> signIn(@RequestBody SignInResource signInResource) {
        Optional<ImmutablePair<User, String>> user = this.userCommandService.handle(SignInCommandFromResourceAssembler.toCommandFromResource(signInResource));
        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AuthenticationUserResource authenticationUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user.get().getLeft(), user.get().getRight());
        return ResponseEntity.ok(authenticationUserResource);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        Optional<User> user = userCommandService.handle(SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource));

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());

        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
