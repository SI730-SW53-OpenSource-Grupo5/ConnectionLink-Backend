package com.connectionlink.backend.forum.interfaces.rest.transform;

import com.connectionlink.backend.forum.domain.model.commands.CreatePostCommand;
import com.connectionlink.backend.forum.interfaces.rest.resources.CreatePostResource;

public class CreatePostCommandFromResourceAssembler {

    public static CreatePostCommand toCommandFromResource(CreatePostResource resource) {

        return new CreatePostCommand(
            resource.title(),
            resource.content(),
            resource.likes(),
            resource.createdAt(),
            resource.userId()
        );

    }

}
