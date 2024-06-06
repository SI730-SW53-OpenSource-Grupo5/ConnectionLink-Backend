package com.connectionlink.backend.UserSubscription.domain.model.commands;

public record RenewUserSubscriptionCommand(Long userSubscriptionId, String newEndDate) {
    public RenewUserSubscriptionCommand {
        if (userSubscriptionId == null) {
            throw new IllegalArgumentException("User Subscription ID cannot be null");
        }
        if (newEndDate == null || newEndDate.isBlank()) {
            throw new IllegalArgumentException("New end date cannot be null or empty");
        }
    }
}