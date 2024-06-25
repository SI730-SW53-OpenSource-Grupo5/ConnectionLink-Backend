package com.connectionlink.backend.forum.interfaces.rest.transform;

import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.interfaces.rest.resources.CommentResource;
import com.connectionlink.backend.forum.interfaces.rest.resources.PostResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;

public class PostResourceFromEntityAssembler {

    public static PostResource toResourceFromEntity(Post entity) {

        // it is used to convert the list of comments to a list of comment resources
        List<CommentResource> comments = entity.getComments().stream()
                .map(CommentResourceFromEntityAssembler::toResourceFromEntity).toList();

        // it is used to convert the user to a user resource
        UserResource user = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser());

        // it is used to create a new postId resource
        return new PostResource(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getLikes(),
                entity.getCreatedAt(),
                user,
                comments
        );

    }

}
