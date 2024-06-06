package com.connectionlink.backend.forum.infraestructure.persistence.jpa;

import com.connectionlink.backend.forum.domain.model.aggregates.Comment;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // this method is used to find all comments by postId id
    List<Comment> findAllByPostId(Long postId);

    // this method is used to find all comments by user id
    List<Comment> findAllByUserId(Long userId);

    // this method is used to find a comment by id
    @NonNull
    Optional<Comment> findById(Long id);

}
