package com.connectionlink.backend.forum.interfaces.rest.transform;

import com.connectionlink.backend.forum.domain.model.commands.CreateCommentCommand;
import com.connectionlink.backend.forum.interfaces.rest.resources.CreateCommentResource;

public class CreateCommentCommandFromResourceAssembler {

    public static CreateCommentCommand toCommandFromResource(CreateCommentResource resource) {

        return new CreateCommentCommand(
            resource.content(),
            resource.likes(),
            resource.createdAt(),
            resource.postId(),
            resource.userId()
        );

    }
}
