package com.connectionlink.backend.forum.interfaces.rest.transform;

import com.connectionlink.backend.forum.domain.model.commands.RemovePostCommand;
import com.connectionlink.backend.forum.interfaces.rest.resources.RemovePostResource;

public class RemovePostCommandFromResourceAssembler {

    public static RemovePostCommand toCommandFromResource(RemovePostResource resource) {

        return new RemovePostCommand(
            resource.postId()
        );

    }

}
