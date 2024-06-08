package com.connectionlink.backend.UserSubscription.domain.model.commands;

public record CreateUserSubscriptionCommand(Long userId, Long subscriptionId, String startDate, String endDate) {
    public CreateUserSubscriptionCommand {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (subscriptionId == null) {
            throw new IllegalArgumentException("Subscription ID cannot be null");
        }
        if (startDate == null || startDate.isBlank()) {
            throw new IllegalArgumentException("Start date cannot be null or empty");
        }
        if (endDate == null || endDate.isBlank()) {
            throw new IllegalArgumentException("End date cannot be null or empty");
        }
    }
}