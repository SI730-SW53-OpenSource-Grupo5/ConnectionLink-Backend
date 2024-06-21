package com.connectionlink.backend.review.interfaces.rest;

import com.connectionlink.backend.review.domain.model.aggregates.Review;
import com.connectionlink.backend.review.domain.model.queries.GetAllReviewBySpecialistUsernameQuery;
import com.connectionlink.backend.review.domain.model.queries.GetAllReviewQuery;
import com.connectionlink.backend.review.domain.model.queries.GetReviewByIdQuery;
import com.connectionlink.backend.review.domain.services.ReviewCommandService;
import com.connectionlink.backend.review.domain.services.ReviewQueryService;
import com.connectionlink.backend.review.interfaces.rest.resources.CreateReviewResource;
import com.connectionlink.backend.review.interfaces.rest.resources.ReviewResource;
import com.connectionlink.backend.review.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import com.connectionlink.backend.review.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    public ReviewController(ReviewQueryService reviewQueryService, ReviewCommandService reviewCommandService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource resource) {
        Optional<Review> review = this.reviewCommandService.handle(CreateReviewCommandFromResourceAssembler.toCommandFromResource(resource));
        return review.map(source -> ResponseEntity.ok(ReviewResourceFromEntityAssembler.toCommandFromResource(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<ReviewResource>> getAllReview() {
        List<Review> reviews = this.reviewQueryService.handle(new GetAllReviewQuery());

        if(reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReviewResource> reviewsResources = reviews.stream().map(ReviewResourceFromEntityAssembler::toCommandFromResource).toList();
        return ResponseEntity.ok(reviewsResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResource> getReviewById(@PathVariable Long id) {
        Optional<Review> review = this.reviewQueryService.handle(new GetReviewByIdQuery(id));

        return review.map(source -> ResponseEntity.ok(ReviewResourceFromEntityAssembler.toCommandFromResource(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/specialist/{username}")
    public ResponseEntity<List<ReviewResource>> getReviewBySpecialistUsername(@PathVariable String username) {
        List<Review> reviews = this.reviewQueryService.handle(new GetAllReviewBySpecialistUsernameQuery(username));

        if(reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReviewResource> reviewsResources = reviews.stream().map(ReviewResourceFromEntityAssembler::toCommandFromResource).toList();
        return ResponseEntity.ok(reviewsResources);
    }
}
