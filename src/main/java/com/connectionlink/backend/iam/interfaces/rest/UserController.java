package com.connectionlink.backend.iam.interfaces.rest;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.domain.model.queries.GetAllPatientUserQuery;
import com.connectionlink.backend.iam.domain.model.queries.GetAllSpecialistUserQuery;
import com.connectionlink.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.connectionlink.backend.iam.domain.model.queries.GetUserByUsernameQuery;
import com.connectionlink.backend.iam.domain.services.UserCommandService;
import com.connectionlink.backend.iam.domain.services.UserQueryService;
import com.connectionlink.backend.iam.interfaces.rest.resources.CreateUserResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.UpdateUserResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.connectionlink.backend.iam.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.connectionlink.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }




    @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody UpdateUserResource resource) {
        Optional<User> user = userCommandService.handle(UpdateUserCommandFromResourceAssembler.toCommandFromResource(resource), id);
        return user.map(source -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        Optional<User> user = userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResource> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userQueryService.handle(new GetUserByUsernameQuery(username));
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/specialists")
    public ResponseEntity<List<UserResource>> getAllSpecialist() {
        List<User> specialists = userQueryService.handle(new GetAllSpecialistUserQuery());

        if(specialists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UserResource> specialistsResource = specialists.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(specialistsResource);
    }
    @GetMapping("")
    public ResponseEntity<List<UserResource>> getAllPatient() {
        List<User> patients = userQueryService.handle(new GetAllPatientUserQuery());

        if(patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UserResource> specialistsResource = patients.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(specialistsResource);
    }

}
