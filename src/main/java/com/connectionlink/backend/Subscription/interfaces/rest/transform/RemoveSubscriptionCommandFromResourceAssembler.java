package com.connectionlink.backend.Subscription.interfaces.rest.transform;

import com.connectionlink.backend.Subscription.domain.model.commands.RemoveSubscriptionCommand;

public class RemoveSubscriptionCommandFromResourceAssembler {
    public static RemoveSubscriptionCommand toCommandFromResource(Long subscriptionId) {
        return new RemoveSubscriptionCommand(subscriptionId);
    }
}