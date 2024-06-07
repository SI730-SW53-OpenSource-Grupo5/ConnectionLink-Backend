package com.connectionlink.backend.forum.domain.services;

import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.domain.model.queries.GetAllPostsByUserIdQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetAllPostsQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetPostByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PostQueryService {

    List<Post> handle(GetAllPostsQuery query);

    List<Post> handle(GetAllPostsByUserIdQuery query);

    Optional<Post> handle(GetPostByIdQuery query);

}
