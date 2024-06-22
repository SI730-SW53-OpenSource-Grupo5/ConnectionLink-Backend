package com.connectionlink.backend.event.interfaces.rest.resources;

import com.connectionlink.backend.category.interfaces.rest.resources.CategoryResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;

import java.util.Date;
import java.util.List;

public record EventResource(Long id, String title, String description, String profileImageUrl, String bannerImageUrl, Date day, CategoryResource category, List<UserResource> users, UserResource specialist) {
}