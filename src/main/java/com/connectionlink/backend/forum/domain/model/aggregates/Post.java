package com.connectionlink.backend.forum.domain.model.aggregates;

import com.connectionlink.backend.forum.domain.model.commands.CreatePostCommand;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    @Setter
    private String title;

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
    @Setter
    private User user;

    // this
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Setter
    private List<Comment> comments;

    protected Post() {
    }

    public Post(CreatePostCommand command, User user) {
        this.title = command.title();
        this.content = command.content();
        this.likes = command.likes();
        this.createdAt = command.createdAt();
        this.user = user;
        this.comments = new ArrayList<>();
    }

}
