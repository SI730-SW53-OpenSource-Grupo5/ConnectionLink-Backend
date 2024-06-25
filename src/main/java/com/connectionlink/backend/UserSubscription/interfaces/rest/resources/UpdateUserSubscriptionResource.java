package com.connectionlink.backend.UserSubscription.interfaces.rest.resources;

public record UpdateUserSubscriptionResource(Long userId, Long subscriptionId, String startDate, String endDate) {
    public UpdateUserSubscriptionResource {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (subscriptionId == null) {
            throw new IllegalArgumentException("Subscription ID cannot be null");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
    }
}
