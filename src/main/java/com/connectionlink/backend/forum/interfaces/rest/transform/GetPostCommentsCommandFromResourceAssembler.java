package com.connectionlink.backend.forum.interfaces.rest.transform;

import com.connectionlink.backend.forum.interfaces.rest.resources.GetPostCommentsResource;

public class GetPostCommentsCommandFromResourceAssembler {

    public static GetPostCommentsResource toCommandFromResource(GetPostCommentsResource resource) {

        return new GetPostCommentsResource(
            resource.postId()
        );

    }

}
