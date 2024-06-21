package com.connectionlink.backend.Subscription.domain.model.queries;

public record GetSubscriptionByIdQuery(Long id) {
    public GetSubscriptionByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Subscription ID cannot be null");
        }
    }
}
