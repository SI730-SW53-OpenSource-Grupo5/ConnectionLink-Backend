package com.connectionlink.backend.forum.application.internal.commandservices;

import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.domain.model.commands.CreatePostCommand;
import com.connectionlink.backend.forum.domain.model.commands.RemovePostCommand;
import com.connectionlink.backend.forum.domain.services.PostCommandService;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.CommentRepository;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.PostRepository;
import com.connectionlink.backend.forum.interfaces.rest.resources.RemovePostResource;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostCommandServiceImpl( UserRepository userRepository,
                                  PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // method to create a postId in the database
    @Override
    public Optional<Post> handle(CreatePostCommand command) {

        User user = this.userRepository.findById(command.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = new Post(command, user);

        var postSaved = this.postRepository.save(post);

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
