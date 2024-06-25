package com.connectionlink.backend.forum.application.internal.commandservices;

import com.connectionlink.backend.forum.domain.model.aggregates.Comment;
import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.domain.model.commands.CreateCommentCommand;
import com.connectionlink.backend.forum.domain.services.CommentCommandService;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.CommentRepository;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.PostRepository;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.UserRepository;
import com.connectionlink.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.connectionlink.backend.notification.domain.services.NotificationCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final NotificationCommandService notificationCommandService;

    public CommentCommandServiceImpl(CommentRepository commentRepository,
                                     UserRepository userRepository,
                                     PostRepository postRepository, NotificationCommandService notificationCommandService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.notificationCommandService = notificationCommandService;
    }

    // method to create a comment in the database
    @Override
    public Optional<Comment> handle(CreateCommentCommand command) {

        User user = this.userRepository.findById(command.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post post = this.postRepository.findById(command.postId()).orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = new Comment(command, user, post);

        var commentSaved = this.commentRepository.save(comment);
        this.notificationCommandService.handle(new CreateNotificationCommand( "Se registro su comentario con exito.",  "Haz realizado un comentario al Post: " + post.getTitle() + ".","/forums/" + post.getId(), user.getUsername()));

        return Optional.of(commentSaved);
    }

}
