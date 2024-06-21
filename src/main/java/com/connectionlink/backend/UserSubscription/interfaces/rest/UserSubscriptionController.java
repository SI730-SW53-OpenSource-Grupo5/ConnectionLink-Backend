package com.connectionlink.backend.UserSubscription.interfaces.rest;

import com.connectionlink.backend.UserSubscription.application.internal.commandservices.UserSubscriptionCommandServiceImpl;
import com.connectionlink.backend.UserSubscription.application.internal.queryservices.UserSubscriptionQueryServiceImpl;
import com.connectionlink.backend.UserSubscription.domain.model.aggregates.UserSubscription;
import com.connectionlink.backend.UserSubscription.domain.model.queries.GetAllUserSubscriptionByUserIdQuery;
import com.connectionlink.backend.UserSubscription.domain.model.queries.GetAllUserSubscriptionByUserUsernameQuery;
import com.connectionlink.backend.UserSubscription.interfaces.rest.resources.UpdateUserSubscriptionResource;
import com.connectionlink.backend.UserSubscription.interfaces.rest.resources.UserSubscriptionResource;
import com.connectionlink.backend.UserSubscription.interfaces.rest.transform.CreateUserSubscriptionCommandFromResourceAssembler;
import com.connectionlink.backend.UserSubscription.interfaces.rest.transform.UpdateUserSubscriptionCommandFromResourceAssembler;
import com.connectionlink.backend.UserSubscription.interfaces.rest.transform.UserSubscriptionResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user-subscriptions")

public class UserSubscriptionController {


    private final UserSubscriptionCommandServiceImpl userSubscriptionCommandService;
    private final UserSubscriptionQueryServiceImpl userSubscriptionQueryService;

    public UserSubscriptionController(UserSubscriptionCommandServiceImpl userSubscriptionCommandService, UserSubscriptionQueryServiceImpl userSubscriptionQueryService) {
        this.userSubscriptionCommandService = userSubscriptionCommandService;
        this.userSubscriptionQueryService = userSubscriptionQueryService;
    }

    @PostMapping
    public ResponseEntity<UserSubscriptionResource> createUserSubscription(@RequestBody UpdateUserSubscriptionResource resource) {
        Optional<UserSubscription> userSubscription = userSubscriptionCommandService.handle(CreateUserSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource));
        return userSubscription.map(source -> new ResponseEntity<>(UserSubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/renew")
    public ResponseEntity<UserSubscriptionResource> renewUserSubscription(@RequestBody UpdateUserSubscriptionResource resource) {
        Optional<UserSubscription> userSubscription = userSubscriptionCommandService.handle(UpdateUserSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource));
        return userSubscription.map(source -> new ResponseEntity<>(UserSubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUserSubscription(@PathVariable Long id) {
        userSubscriptionCommandService.handleRemove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSubscriptionResource>> getAllUserSubscriptionsByUserId(@PathVariable Long userId) {
        List<UserSubscription> userSubscriptions = userSubscriptionQueryService.handle(new GetAllUserSubscriptionByUserIdQuery(userId));

        if (userSubscriptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UserSubscriptionResource> resources = userSubscriptions.stream()
                .map(UserSubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<UserSubscriptionResource>> getAllUserSubscriptionsByUsername(@PathVariable String username) {
        List<UserSubscription> userSubscriptions = userSubscriptionQueryService.handle(new GetAllUserSubscriptionByUserUsernameQuery(username));

        if (userSubscriptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<UserSubscriptionResource> resources = userSubscriptions.stream()
                .map(UserSubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
