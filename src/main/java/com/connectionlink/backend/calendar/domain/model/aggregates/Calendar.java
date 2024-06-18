package com.connectionlink.backend.calendar.domain.model.aggregates;

import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.domain.model.queries.GetAllEventQuery;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Calendar {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    private Long Id;

    @Column(nullable = false)
    @Getter
    @Setter
    private Event event;

    @Column(nullable = false)
    @Getter
    @Setter
    private Date day;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User specialist;

    @Getter
    @Setter
    private List<Event> events;

    protected Calendar() {
    }

    public Calendar( Date day, Event event) {
        this.day = day;
        this.event=event;
    }
}
