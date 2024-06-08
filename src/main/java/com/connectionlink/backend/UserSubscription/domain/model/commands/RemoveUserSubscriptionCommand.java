package com.connectionlink.backend.UserSubscription.domain.model.commands;

public record RemoveUserSubscriptionCommand(Long userSubscriptionId) {
    public RemoveUserSubscriptionCommand {
        if (userSubscriptionId == null) {
            throw new IllegalArgumentException("User Subscription ID cannot be null");
        }
    }
}