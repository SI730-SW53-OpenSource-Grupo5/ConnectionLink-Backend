package com.connectionlink.backend.forum.domain.services;

import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.domain.model.commands.CreatePostCommand;
import com.connectionlink.backend.forum.domain.model.commands.RemovePostCommand;

import java.util.Optional;

public interface PostCommandService {

    Optional<Post> handle(CreatePostCommand command);

    // method to delete a postId by id
    Optional<Post> handle(RemovePostCommand command);

}
