package com.connectionlink.backend.forum.domain.model.aggregates;

import com.connectionlink.backend.forum.domain.model.commands.CreateCommentCommand;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    @Setter
    private String content;

    @Column(nullable = false)
    @Setter
    private Integer likes;

    // this column is used to store the date when the comment was created
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    // this column is used to store the id of the user who created the comment
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    @Setter
    private User user;

    // this field is used to store the postId to which the comment belongs
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference
    @Setter
    private Post post;

    protected Comment() {

    }

    public Comment(CreateCommentCommand command, User user, Post post) {
        this.content = command.content();
        this.likes = command.likes();
        this.createdAt = command.createdAt();
        this.user = user;
        this.post = post;
    }

}
