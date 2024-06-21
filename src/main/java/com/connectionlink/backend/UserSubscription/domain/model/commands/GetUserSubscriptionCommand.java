package com.connectionlink.backend.UserSubscription.domain.model.commands;

public record GetUserSubscriptionCommand(Long userSubscriptionId) {
    public GetUserSubscriptionCommand {
        if (userSubscriptionId == null) {
            throw new IllegalArgumentException("User Subscription ID cannot be null");
        }
    }
}