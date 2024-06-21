package com.connectionlink.backend.review.domain.services;

import com.connectionlink.backend.review.domain.model.aggregates.Review;
import com.connectionlink.backend.review.domain.model.commands.CreateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(CreateReviewCommand command);
}
