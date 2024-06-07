package com.connectionlink.backend.forum.interfaces.rest;

import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import com.connectionlink.backend.forum.domain.model.commands.RemovePostCommand;
import com.connectionlink.backend.forum.domain.model.queries.GetAllPostsByUserIdQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetAllPostsQuery;
import com.connectionlink.backend.forum.domain.model.queries.GetPostByIdQuery;
import com.connectionlink.backend.forum.domain.services.PostCommandService;
import com.connectionlink.backend.forum.domain.services.PostQueryService;
import com.connectionlink.backend.forum.interfaces.rest.resources.CreatePostResource;
import com.connectionlink.backend.forum.interfaces.rest.resources.PostResource;
import com.connectionlink.backend.forum.interfaces.rest.transform.CreatePostCommandFromResourceAssembler;
import com.connectionlink.backend.forum.interfaces.rest.transform.PostResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {

    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    public PostController(PostCommandService postCommandService
            , PostQueryService postQueryService) {
        this.postCommandService = postCommandService;
        this.postQueryService = postQueryService;
    }

    // this method creates a postId in the database and returns the postId created
    @PostMapping
    public ResponseEntity<PostResource> createPost(@RequestBody CreatePostResource resource) {

        Optional<Post> post = postCommandService.handle(CreatePostCommandFromResourceAssembler.toCommandFromResource(resource));

        return post.map(source -> ResponseEntity.ok(PostResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResource> deletePost(@PathVariable Long id) {

        RemovePostCommand command = new RemovePostCommand(id);
        Optional<Post> post = postCommandService.handle(command);

        if (post.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("")
    public ResponseEntity<List<PostResource>> getAllPost() {
        List<Post> posts = postQueryService.handle(new GetAllPostsQuery());

        if(posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PostResource> postsResource = posts.stream().map(PostResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(postsResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResource> getPostById(@PathVariable Long id) {
        Optional<Post> post = postQueryService.handle(new GetPostByIdQuery(id));

        return post.map(source -> ResponseEntity.ok(PostResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // check the method below
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResource>> getAllPostByUserId(@PathVariable Long userId) {
        List<Post> posts = postQueryService.handle(new GetAllPostsByUserIdQuery(userId));

        if(posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PostResource> postsResource = posts.stream().map(PostResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(postsResource);
    }


}
