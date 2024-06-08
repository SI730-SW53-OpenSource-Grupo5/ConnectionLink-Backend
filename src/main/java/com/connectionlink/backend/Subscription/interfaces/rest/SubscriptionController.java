package com.connectionlink.backend.Subscription.interfaces.rest;


import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import com.connectionlink.backend.Subscription.domain.model.commands.RemoveSubscriptionCommand;
import com.connectionlink.backend.Subscription.domain.model.queries.GetAllSubscriptionsQuery;
import com.connectionlink.backend.Subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.connectionlink.backend.Subscription.domain.services.SubscriptionCommandService;
import com.connectionlink.backend.Subscription.domain.services.SubscriptionQueryService;
import com.connectionlink.backend.Subscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.connectionlink.backend.Subscription.interfaces.rest.resources.UpdateSubscriptionResource;
import com.connectionlink.backend.Subscription.interfaces.rest.resources.SubscriptionResource;
import com.connectionlink.backend.Subscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.connectionlink.backend.Subscription.interfaces.rest.transform.UpdateSubscriptionCommandFromResourceAssembler;
import com.connectionlink.backend.Subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/subscriptions")
public class SubscriptionController {
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;

    public SubscriptionController(SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource resource) {
        Optional<Subscription> subscription = subscriptionCommandService.handle(CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource));
        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResource> updateSubscription(@PathVariable Long id, @RequestBody UpdateSubscriptionResource resource) {
        Optional<Subscription> subscription = subscriptionCommandService.handle(UpdateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource), id);
        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeSubscription(@PathVariable Long id) {
        subscriptionCommandService.handle(new RemoveSubscriptionCommand(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResource>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionQueryService.handle(new GetAllSubscriptionsQuery());
        if (subscriptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<SubscriptionResource> resources = subscriptions.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionQueryService.handle(new GetSubscriptionByIdQuery(id));
        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}