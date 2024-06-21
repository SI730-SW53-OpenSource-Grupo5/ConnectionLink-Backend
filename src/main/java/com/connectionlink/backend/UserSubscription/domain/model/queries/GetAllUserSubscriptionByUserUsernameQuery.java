package com.connectionlink.backend.UserSubscription.domain.model.queries;

public record GetAllUserSubscriptionByUserUsernameQuery(String username) {
    public GetAllUserSubscriptionByUserUsernameQuery {
        if (username == null) {
            throw new IllegalArgumentException("User username cannot be null");
        }
    }
}
