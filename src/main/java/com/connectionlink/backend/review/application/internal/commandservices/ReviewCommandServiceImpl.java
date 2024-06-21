package com.connectionlink.backend.review.application.internal.commandservices;

import com.connectionlink.backend.review.domain.model.aggregates.Review;
import com.connectionlink.backend.review.domain.model.commands.CreateReviewCommand;
import com.connectionlink.backend.review.domain.services.ReviewCommandService;
import com.connectionlink.backend.review.infrastructure.persistence.jpa.ReviewRepository;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
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

        return Optional.of(reviewSaved);
    }
}