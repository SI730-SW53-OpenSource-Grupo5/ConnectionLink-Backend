package com.connectionlink.backend.forum.application.internal.commandservices;

import com.connectionlink.backend.forum.domain.model.aggregates.Comment;
import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.domain.model.commands.CreateCommentCommand;
import com.connectionlink.backend.forum.domain.services.CommentCommandService;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.CommentRepository;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.PostRepository;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentCommandServiceImpl(CommentRepository commentRepository,
                                     UserRepository userRepository,
                                     PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // method to create a comment in the database
    @Override
    public Optional<Comment> handle(CreateCommentCommand command) {

        User user = this.userRepository.findById(command.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post post = this.postRepository.findById(command.postId()).orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = new Comment(command, user, post);

        var commentSaved = this.commentRepository.save(comment);

        return Optional.of(commentSaved);
    }

}
