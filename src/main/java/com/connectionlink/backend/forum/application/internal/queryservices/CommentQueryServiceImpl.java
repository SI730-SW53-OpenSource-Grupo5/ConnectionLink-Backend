package com.connectionlink.backend.forum.application.internal.queryservices;

import com.connectionlink.backend.forum.domain.model.aggregates.Comment;
import com.connectionlink.backend.forum.domain.model.queries.GetAllCommentsByPostIdQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetAllCommentsByUserIdQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetCommentByIdQuery;
import com.connectionlink.backend.forum.domain.services.CommentQueryService;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {

    private final CommentRepository commentRepository;

    public CommentQueryServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> handle(GetAllCommentsByUserIdQuery query) {
        return this.commentRepository.findAllByUserId(query.userId());
    }

    @Override
    public List<Comment> handle(GetAllCommentsByPostIdQuery query) {
        return this.commentRepository.findAllByPostId(query.postId());
    }

    @Override
    public Optional<Comment> handle(GetCommentByIdQuery query) {
        return this.commentRepository.findById(query.commentId());
    }

}
