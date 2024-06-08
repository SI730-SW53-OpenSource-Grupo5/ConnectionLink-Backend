package com.connectionlink.backend.Subscription.application.internal.commandservices;

import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import com.connectionlink.backend.Subscription.domain.model.commands.CreateSubscriptionCommand;
import com.connectionlink.backend.Subscription.domain.model.commands.RemoveSubscriptionCommand;
import com.connectionlink.backend.Subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.connectionlink.backend.Subscription.domain.services.SubscriptionCommandService;
import com.connectionlink.backend.Subscription.infrastructure.persitence.jpa.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        Subscription subscription = new Subscription(
                command.name(),
                command.description(),
                command.duration(),
                command.price()
        );

        Subscription savedSubscription = this.subscriptionRepository.save(subscription);
        return Optional.of(savedSubscription);
    }

    @Override
    public Optional<Subscription> handle(RemoveSubscriptionCommand command) {
        Optional<Subscription> subscription = this.subscriptionRepository.findById(command.subscriptionId());
        subscription.ifPresent(this.subscriptionRepository::delete);
        return subscription;
    }

    @Override
    public Optional<Subscription> handle(UpdateSubscriptionCommand command, Long id) {
        Optional<Subscription> subscription = this.subscriptionRepository.findById(id);
        subscription.ifPresent(sub -> {
            sub.setName(command.name());
            sub.setDescription(command.description());
            sub.setDuration(command.duration());
            sub.setPrice(command.price());
            this.subscriptionRepository.save(sub);
        });
        return subscription;
    }
}