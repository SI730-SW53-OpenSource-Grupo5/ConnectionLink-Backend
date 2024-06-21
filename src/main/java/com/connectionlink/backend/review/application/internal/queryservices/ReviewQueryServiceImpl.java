package com.connectionlink.backend.review.application.internal.queryservices;

import com.connectionlink.backend.review.domain.model.aggregates.Review;
import com.connectionlink.backend.review.domain.model.queries.GetAllReviewBySpecialistUsernameQuery;
import com.connectionlink.backend.review.domain.model.queries.GetAllReviewQuery;
import com.connectionlink.backend.review.domain.model.queries.GetReviewByIdQuery;
import com.connectionlink.backend.review.domain.services.ReviewQueryService;
import com.connectionlink.backend.review.infrastructure.persistence.jpa.ReviewRepository;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService  {

    private final ReviewRepository reviewRepository;
    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewQuery query) {
        return this.reviewRepository.findAll();
    }

    @Override
    public List<Review> handle(GetAllReviewBySpecialistUsernameQuery query) {
        return this.reviewRepository.findBySpecialistUsername(query.username());
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return this.reviewRepository.findById(query.id());
    }
}
