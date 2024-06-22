package com.connectionlink.backend.review.interfaces.rest.transform;

import com.connectionlink.backend.review.domain.model.aggregates.Review;
import com.connectionlink.backend.review.interfaces.rest.resources.ReviewResource;
import com.connectionlink.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toCommandFromResource(Review entity) {
        return new ReviewResource(entity.getId(), entity.getDescription(), UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser()), UserResourceFromEntityAssembler.toResourceFromEntity(entity.getSpecialist()));
    }
}
