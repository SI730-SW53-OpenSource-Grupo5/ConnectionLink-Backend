package com.connectionlink.backend.UserSubscription.application.internal.commandservices;

import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import com.connectionlink.backend.Subscription.infrastructure.persitence.jpa.SubscriptionRepository;
import com.connectionlink.backend.UserSubscription.domain.model.aggregates.UserSubscription;
import com.connectionlink.backend.UserSubscription.domain.model.commands.CreateUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.domain.model.commands.GetUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.domain.model.commands.RemoveUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.domain.model.commands.RenewUserSubscriptionCommand;
import com.connectionlink.backend.UserSubscription.domain.services.UserSubscriptionCommandService;
import com.connectionlink.backend.UserSubscription.infrastructure.persitence.jpa.UserSubscriptionRepository;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserSubscriptionCommandServiceImpl implements UserSubscriptionCommandService {

    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public UserSubscriptionCommandServiceImpl(UserSubscriptionRepository userSubscriptionRepository, UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<UserSubscription> handle(CreateUserSubscriptionCommand command) {
        User user = userRepository.findById(command.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Subscription subscription = subscriptionRepository.findById(command.subscriptionId()).orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        UserSubscription userSubscription = new UserSubscription(
                user,
                subscription,
                LocalDate.parse(command.startDate()),
                LocalDate.parse(command.endDate())
        );

        var userSubscriptionSaved = userSubscriptionRepository.save(userSubscription);
        return Optional.of(userSubscriptionSaved);
    }

    @Override
    public Optional<UserSubscription> handle(GetUserSubscriptionCommand command) {
        return userSubscriptionRepository.findById(command.userSubscriptionId());
    }

    @Override
    public Optional<UserSubscription> handle(RemoveUserSubscriptionCommand command) {
        Optional<UserSubscription> userSubscription = userSubscriptionRepository.findById(command.userSubscriptionId());
        userSubscription.ifPresent(userSubscriptionRepository::delete);
        return userSubscription;
    }

    @Override
    public Optional<UserSubscription> handle(RenewUserSubscriptionCommand command) {
        Optional<UserSubscription> userSubscription = userSubscriptionRepository.findById(command.userSubscriptionId());
        userSubscription.ifPresent(subscription -> {
            subscription.setEndDate(LocalDate.parse(command.newEndDate()));
            userSubscriptionRepository.save(subscription);
        });
        return userSubscription;
    }
    @Override
    public void handleRemove(Long userSubscriptionId) {
        userSubscriptionRepository.findById(userSubscriptionId).ifPresent(userSubscriptionRepository::delete);
    }
}
