package com.connectionlink.backend.Subscription.domain.services;

import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import com.connectionlink.backend.Subscription.domain.model.commands.CreateSubscriptionCommand;
import com.connectionlink.backend.Subscription.domain.model.commands.RemoveSubscriptionCommand;
import com.connectionlink.backend.Subscription.domain.model.commands.UpdateSubscriptionCommand;

import java.util.Optional;

public interface SubscriptionCommandService {

    Optional<Subscription> handle(CreateSubscriptionCommand command);
    Optional<Subscription> handle(RemoveSubscriptionCommand command);
    Optional<Subscription> handle(UpdateSubscriptionCommand command, Long id);
}
