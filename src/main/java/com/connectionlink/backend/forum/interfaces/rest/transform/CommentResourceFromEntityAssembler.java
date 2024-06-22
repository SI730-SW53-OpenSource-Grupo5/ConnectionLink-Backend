package com.connectionlink.backend.forum.interfaces.rest.transform;

import com.connectionlink.backend.forum.domain.model.aggregates.Comment;
import com.connectionlink.backend.forum.interfaces.rest.resources.CommentResource;
import com.connectionlink.backend.forum.interfaces.rest.resources.PostResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class CommentResourceFromEntityAssembler {

    public static CommentResource toResourceFromEntity(Comment entity) {

        PostResource post = PostResourceFromEntityAssembler.toResourceFromEntity(entity.getPost());
        UserResource user = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser());

        return new CommentResource(
                entity.getId(),
                entity.getContent(),
                entity.getLikes(),
                entity.getCreatedAt(),
                post.Id(),
                user
        );
    }

}
