package com.connectionlink.backend.calendar.domain.model.aggregates;

import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Calendar {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    private Long Id;

    @Column(nullable = false)
    @Getter
    @Setter
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    @Getter
    @Setter
    private Day day;

    @ManyToOne
    @JoinColumn(name = "hour_id", nullable = false)
    @Getter
    @Setter
    private Hour hour;

    @Column(nullable = false, updatable = false)
    @Getter
    private String url;

    @ManyToOne
    @JoinColumn(name = "specialist_id", nullable = false)
    @Getter
    @Setter
    private User specialist;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;


    protected Calendar() {}

    public Calendar(CreateCalendarCommand command, Day day, Hour hour, User specialist) {
        this.url = command.url();
        this.day = day;
        this.hour = hour;
        this.specialist = specialist;
        this.isAvailable = true;
    }
}
