package com.connectionlink.backend.UserSubscription.interfaces.rest.transform;

import com.connectionlink.backend.UserSubscription.domain.model.commands.CreateUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.interfaces.rest.resources.UpdateUserSubscriptionResource;

public class CreateUserSubscriptionCommandFromResourceAssembler {
    public static CreateUserSubscriptionCommand toCommandFromResource(UpdateUserSubscriptionResource resource) {
        return new CreateUserSubscriptionCommand(
                resource.userId(),
                resource.subscriptionId(),
                resource.startDate(),
                resource.endDate()
        );
    }
}
