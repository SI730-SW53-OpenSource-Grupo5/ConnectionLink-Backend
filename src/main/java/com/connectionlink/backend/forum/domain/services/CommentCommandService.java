package com.connectionlink.backend.forum.domain.services;

import com.connectionlink.backend.forum.domain.model.aggregates.Comment;
import com.connectionlink.backend.forum.domain.model.commands.CreateCommentCommand;

import java.util.Optional;

public interface CommentCommandService {

    // method to create a comment in the database
    Optional<Comment> handle(CreateCommentCommand command);

}
