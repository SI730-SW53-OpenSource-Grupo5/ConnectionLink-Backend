package com.connectionlink.backend.UserSubscription.domain.model.queries;

public record GetAllUserSubscriptionByUserIdQuery(Long userId) {
    public GetAllUserSubscriptionByUserIdQuery {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
    }
}
