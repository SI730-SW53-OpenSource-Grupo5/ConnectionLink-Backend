package com.connectionlink.backend.forum.interfaces.rest.resources;

import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;

import java.util.Date;
import java.util.List;

public record PostResource(Long Id, String title, String content, Integer likes, Date createdAt, UserResource user, List<CommentResource> comments) {
}
