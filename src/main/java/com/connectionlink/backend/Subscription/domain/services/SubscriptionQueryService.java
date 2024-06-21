package com.connectionlink.backend.Subscription.domain.services;

import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import com.connectionlink.backend.Subscription.domain.model.queries.GetAllSubscriptionsQuery;
import com.connectionlink.backend.Subscription.domain.model.queries.GetSubscriptionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SubscriptionQueryService {
    List<Subscription> handle(GetAllSubscriptionsQuery query);
    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
}
