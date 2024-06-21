package com.connectionlink.backend.UserSubscription.interfaces.rest.transform;

import com.connectionlink.backend.UserSubscription.domain.model.commands.RenewUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.interfaces.rest.resources.UpdateUserSubscriptionResource;


public class UpdateUserSubscriptionCommandFromResourceAssembler {

    public static RenewUserSubscriptionCommand toCommandFromResource(UpdateUserSubscriptionResource resource) {
        return new RenewUserSubscriptionCommand(
                resource.userId(),
                resource.endDate()
        );
    }
}
