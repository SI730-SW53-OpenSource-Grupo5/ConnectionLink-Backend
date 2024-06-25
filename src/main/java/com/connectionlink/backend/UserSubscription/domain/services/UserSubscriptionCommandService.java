package com.connectionlink.backend.UserSubscription.domain.services;


import com.connectionlink.backend.UserSubscription.domain.model.aggregates.UserSubscription;
import com.connectionlink.backend.UserSubscription.domain.model.commands.CreateUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.domain.model.commands.RenewUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.domain.model.commands.RemoveUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.domain.model.commands.GetUserSubscriptionCommand;

import java.util.Optional;


public interface UserSubscriptionCommandService {
    Optional<UserSubscription> handle(CreateUserSubscriptionCommand command);
    Optional<UserSubscription> handle(GetUserSubscriptionCommand command);
    Optional<UserSubscription> handle(RemoveUserSubscriptionCommand command);
    Optional<UserSubscription> handle(RenewUserSubscriptionCommand command);
    void handleRemove(Long userSubscriptionId);

}
