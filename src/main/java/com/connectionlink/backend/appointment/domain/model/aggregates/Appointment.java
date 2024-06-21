package com.connectionlink.backend.appointment.domain.model.aggregates;

import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;
import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
    @Setter
    private Calendar calendar;

    public Appointment() {
    }

    public Appointment(CreateAppointmentCommand command, User specialist, User user, Calendar calendar)
    {
        this.createdAt = command.createdAt();
        this.user = user;
        this.calendar = calendar;
    }
}
