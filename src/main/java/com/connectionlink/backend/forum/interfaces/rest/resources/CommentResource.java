package com.connectionlink.backend.forum.interfaces.rest.resources;
import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;

import java.util.Date;

public record CommentResource(Long Id, String content, Integer likes, Date createdAt, Long postId, UserResource user) { }
