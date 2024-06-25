package com.connectionlink.backend.review.interfaces.rest.resources;

import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;

public record ReviewResource(Long id, String description, UserResource specialist, UserResource user) {
}
