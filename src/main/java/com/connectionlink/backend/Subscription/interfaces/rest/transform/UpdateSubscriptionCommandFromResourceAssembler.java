package com.connectionlink.backend.Subscription.interfaces.rest.transform;

import com.connectionlink.backend.Subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.connectionlink.backend.Subscription.interfaces.rest.resources.UpdateSubscriptionResource;

public class UpdateSubscriptionCommandFromResourceAssembler {
    public static UpdateSubscriptionCommand toCommandFromResource(UpdateSubscriptionResource resource) {
        return new UpdateSubscriptionCommand(
                resource.id(),
                resource.name(),
                resource.description(),
                resource.duration(),
                resource.price()
        );
    }
}