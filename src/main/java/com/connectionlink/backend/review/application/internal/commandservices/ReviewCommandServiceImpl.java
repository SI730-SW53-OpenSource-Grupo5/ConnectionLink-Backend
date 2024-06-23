package com.connectionlink.backend.review.application.internal.commandservices;

import com.connectionlink.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.connectionlink.backend.notification.domain.services.NotificationCommandService;
import com.connectionlink.backend.review.domain.model.aggregates.Review;
import com.connectionlink.backend.review.domain.model.commands.CreateReviewCommand;
import com.connectionlink.backend.review.domain.services.ReviewCommandService;
import com.connectionlink.backend.review.infrastructure.persistence.jpa.ReviewRepository;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final NotificationCommandService notificationCommandService;
    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, NotificationCommandService notificationCommandService) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.notificationCommandService = notificationCommandService;
    }

    @Override
    public Optional<Review> handle(CreateReviewCommand command) {
        User user = this.userRepository.findByUsername(command.userUsername()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        User specialist = this.userRepository.findByUsername(command.specialistUsername()).orElseThrow(() -> new IllegalArgumentException("Specialist not found"));

        if(!specialist.getIsSpecialistUser()) {
            throw new IllegalArgumentException("The user entered is not that of a specialist ");
        }
        if(user.getUsername() == specialist.getUsername()) {
            throw new IllegalArgumentException("The user cannot make the same review ");
        }

        Review review = new Review(command, specialist, user);

        var reviewSaved = this.reviewRepository.save(review);
        this.notificationCommandService.handle(new CreateNotificationCommand( "Se registro su review con exito.",  "Haz realizado un review al Especialist: " + specialist.getUsername() + ".","/specialist/" + specialist.getUsername(), command.userUsername()));
        this.notificationCommandService.handle(new CreateNotificationCommand( "Te han realizado un review.",  "El usuario " + user.getUsername() + " te ha realizado un review.","/specialist/" + specialist.getUsername(), command.specialistUsername()));

        return Optional.of(reviewSaved);
    }
}
