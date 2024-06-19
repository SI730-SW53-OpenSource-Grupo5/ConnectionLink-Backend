package com.connectionlink.backend.calendar.domain.model.aggregates;

import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    @Setter
    private User specialist;

    @Column(nullable = false)
    @Setter
    private Date day;

    @Column(nullable = false)
    @Setter
    private boolean isAvailable;

    public Calendar() {
    }

    public Calendar(CreateCalendarCommand command, User specialist) {
        this.specialist = specialist;
        this.day = command.day();
        this.isAvailable = command.isAvailable();
    }
}
