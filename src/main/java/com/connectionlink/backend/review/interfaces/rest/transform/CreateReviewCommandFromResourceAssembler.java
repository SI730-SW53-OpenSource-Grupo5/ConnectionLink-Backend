package com.connectionlink.backend.review.interfaces.rest.transform;

import com.connectionlink.backend.review.domain.model.commands.CreateReviewCommand;
import com.connectionlink.backend.review.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(resource.description(), resource.specialistUsername(), resource.userUsername());
    }
}
