package com.connectionlink.backend.forum.application.internal.commandservices;

import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.domain.model.commands.CreatePostCommand;
import com.connectionlink.backend.forum.domain.model.commands.RemovePostCommand;
import com.connectionlink.backend.forum.domain.services.PostCommandService;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.PostRepository;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.UserRepository;
import com.connectionlink.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.connectionlink.backend.notification.domain.services.NotificationCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final NotificationCommandService notificationCommandService;

    public PostCommandServiceImpl(UserRepository userRepository,
                                  PostRepository postRepository, NotificationCommandService notificationCommandService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.notificationCommandService = notificationCommandService;
    }

    // method to create a postId in the database
    @Override
    public Optional<Post> handle(CreatePostCommand command) {

        User user = this.userRepository.findById(command.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = new Post(command, user);

        var postSaved = this.postRepository.save(post);
        this.notificationCommandService.handle(new CreateNotificationCommand( "Se registro su post con exito.",  "Haz realizado un post: " + postSaved.getTitle() + ".","/forums/" + postSaved.getId(), user.getUsername()));

        return Optional.of(postSaved);
    }

    // method to delete a postId by id
    @Override
    public Optional<Post> handle(RemovePostCommand command) {

        Post post = this.postRepository.findById(command.postId()).orElseThrow(() -> new IllegalArgumentException("Post not found"));

        this.postRepository.delete(post);

        return Optional.of(post);
    }


}
