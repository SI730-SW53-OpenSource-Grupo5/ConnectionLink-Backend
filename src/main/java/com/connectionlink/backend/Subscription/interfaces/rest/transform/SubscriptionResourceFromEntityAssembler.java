package com.connectionlink.backend.Subscription.interfaces.rest.transform;

import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import com.connectionlink.backend.Subscription.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDuration(),
                entity.getPrice()
        );
    }
}