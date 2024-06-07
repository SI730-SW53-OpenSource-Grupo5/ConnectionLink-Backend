package com.connectionlink.backend.forum.domain.services;

import com.connectionlink.backend.forum.domain.model.aggregates.Comment;
import com.connectionlink.backend.forum.domain.model.queries.GetAllCommentsByPostIdQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetAllCommentsByUserIdQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetCommentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CommentQueryService {

    List<Comment> handle(GetAllCommentsByUserIdQuery query);

    List<Comment> handle(GetAllCommentsByPostIdQuery query);

    Optional<Comment> handle(GetCommentByIdQuery query);

}
