package com.connectionlink.backend.forum.interfaces.rest;

import com.connectionlink.backend.forum.domain.model.aggregates.Comment;
import com.connectionlink.backend.forum.domain.model.queries.GetAllCommentsByPostIdQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetCommentByIdQuery;
import com.connectionlink.backend.forum.domain.services.CommentCommandService;
import com.connectionlink.backend.forum.domain.services.CommentQueryService;
import com.connectionlink.backend.forum.interfaces.rest.resources.CommentResource;
import com.connectionlink.backend.forum.interfaces.rest.resources.CreateCommentResource;
import com.connectionlink.backend.forum.interfaces.rest.transform.CommentResourceFromEntityAssembler;
import com.connectionlink.backend.forum.interfaces.rest.transform.CreateCommentCommandFromResourceAssembler;
import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/comments", produces = APPLICATION_JSON_VALUE)
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    public CommentController(CommentCommandService commentCommandService,
                             CommentQueryService commentQueryService) {
        this.commentCommandService = commentCommandService;
        this.commentQueryService = commentQueryService;
    }

    @PostMapping
    public ResponseEntity<CommentResource> createComment(@RequestBody CreateCommentResource resource) {

        Optional<Comment> comment = commentCommandService.handle(CreateCommentCommandFromResourceAssembler.toCommandFromResource(resource));

        //return comment.map(source -> ResponseEntity.ok(CommentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());

        return comment.map(source -> {
            Long postId = source.getPost().getId();
            UserResource user = UserResourceFromEntityAssembler.toResourceFromEntity(source.getUser());
            return ResponseEntity.ok(new CommentResource(
                    source.getId(),
                    source.getContent(),
                    source.getLikes(),
                    source.getCreatedAt(),
                    postId,
                    user
            ));
        }).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping("post/{id}")
    public ResponseEntity<List<CommentResource>> getAllCommentsByPostId(@PathVariable Long id) {
        List<Comment> comments = commentQueryService.handle(new GetAllCommentsByPostIdQuery(id));

        if(comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CommentResource> commentsResource = comments.stream().map(CommentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(commentsResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResource> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentQueryService.handle(new GetCommentByIdQuery(id));

        return comment.map(source -> ResponseEntity.ok(CommentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
