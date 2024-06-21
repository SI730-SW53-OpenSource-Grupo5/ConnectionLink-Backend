package com.connectionlink.backend.calendar.domain.model.aggregates;

import com.connectionlink.backend.calendar.domain.model.commands.CreateDayCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Day {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    private Long Id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String day;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected Day() {}

    public Day(CreateDayCommand command) {
        this.day = command.day();
    }
}
