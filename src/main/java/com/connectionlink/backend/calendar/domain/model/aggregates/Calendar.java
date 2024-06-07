package com.connectionlink.backend.calendar.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long Id;

    @Getter
    private Boolean available;

    //@Column(nullable = false)
    //@Getter
    //@Setter
    //private Speialist speialist;

    @Column(nullable = false)
    @Getter
    @Setter
    private Day day;

    @Column(nullable = false)
    @Getter
    @Setter
    private Hour hour;

    public Calendar(Day day, Hour hour, Boolean available, Speialist speialist) {
        this.hour = hour;
        this.day = day;
        this.available = available;
        this.speialist = speialist;
    }
}
