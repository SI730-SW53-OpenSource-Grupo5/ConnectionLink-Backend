package com.connectionlink.backend.forum.application.internal.queryservices;

import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.domain.model.queries.GetAllPostsByUserIdQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetAllPostsQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetPostByIdQuery;
import com.connectionlink.backend.forum.domain.services.PostQueryService;
import com.connectionlink.backend.forum.infraestructure.persistence.jpa.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;

    public PostQueryServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> handle(GetAllPostsByUserIdQuery query) {
        return this.postRepository.findAllByUserId(query.userId());
    }

    // if you get all posts only use findAll() method, dont use findAllPosts() method for example
    @Override
    public List<Post> handle(GetAllPostsQuery query) {
        return this.postRepository.findAll();
    }

    @Override
    public Optional<Post> handle(GetPostByIdQuery query) {
        return this.postRepository.findById(query.postId());
    }


}
