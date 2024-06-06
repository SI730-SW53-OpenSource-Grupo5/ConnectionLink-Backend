package com.connectionlink.backend.UserSubscription.interfaces.rest.transform;

import com.connectionlink.backend.UserSubscription.domain.model.aggregates.UserSubscription;
import com.connectionlink.backend.UserSubscription.interfaces.rest.resources.UserSubscriptionResource;

public class UserSubscriptionResourceFromEntityAssembler {
    public static UserSubscriptionResource toResourceFromEntity(UserSubscription entity) {
        return new UserSubscriptionResource(
                entity.getId(),
                entity.getUser().getId(),
                entity.getSubscription().getId(),
                entity.getStartDate().toString(),
                entity.getEndDate().toString()
        );
    }
}
