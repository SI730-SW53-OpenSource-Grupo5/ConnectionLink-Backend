package com.connectionlink.backend.Subscription.domain.model.commands;

public record RemoveSubscriptionCommand(Long subscriptionId) {
    public RemoveSubscriptionCommand {
        if (subscriptionId == null) {
            throw new IllegalArgumentException("Subscription ID cannot be null");
        }
    }
}
