package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "user_action_tracing")
@Data
@EntityListeners(AuditingEntityListener.class) // date
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "event_id")
public class UserActionTracing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "event_id", nullable = false )
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    @JsonIgnore
    private Commodity commodity;

    @CreatedDate
    @Column(name = "tracing_time", nullable = false, updatable = false)
    private LocalDateTime tracingTime;

    @Column(name = "tracing_type", nullable = false)
    private Integer tracingType;

}
