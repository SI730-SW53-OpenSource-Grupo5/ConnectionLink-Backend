package com.connectionlink.backend.Subscription.interfaces.rest.transform;


import com.connectionlink.backend.Subscription.domain.model.commands.CreateSubscriptionCommand;
import com.connectionlink.backend.Subscription.interfaces.rest.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
                resource.name(),
                resource.description(),
                resource.duration(),
                resource.price()
        );
    }
}