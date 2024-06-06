package com.connectionlink.backend.forum.infraestructure.persistence.jpa;

import com.connectionlink.backend.forum.domain.model.aggregates.Post;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // it is used to find a postId by id
    @NonNull
    Optional<Post> findById(Long id);

    Optional<Post> findPostByUserId (Long userId);

    // it is works as a query method to find all posts by user id
    List<Post> findAllByUserId(Long userId);

    @NonNull
    List<Post> findAll();

}
