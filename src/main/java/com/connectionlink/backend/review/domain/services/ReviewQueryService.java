package com.connectionlink.backend.review.domain.services;

import com.connectionlink.backend.review.domain.model.aggregates.Review;
import com.connectionlink.backend.review.domain.model.queries.GetAllReviewBySpecialistUsernameQuery;
import com.connectionlink.backend.review.domain.model.queries.GetAllReviewQuery;
import com.connectionlink.backend.review.domain.model.queries.GetReviewByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewQuery query);

    List<Review> handle(GetAllReviewBySpecialistUsernameQuery query);

    Optional<Review> handle(GetReviewByIdQuery query);
}
