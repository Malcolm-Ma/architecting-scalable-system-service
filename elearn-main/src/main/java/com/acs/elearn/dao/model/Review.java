package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class) // date
@Data
public class Review implements Serializable {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //generate 32length UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name = "review_id", nullable = false, length = 32)
    private String reviewId;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    @JsonIgnoreProperties({"review_list"})
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"review_list"})
    private User user;

    @CreatedDate
    @Column(name = "review_create_time", nullable = false, updatable = false)
    private LocalDateTime reviewCreateTime;

    @Column(name = "review_comment", columnDefinition = "TEXT")
    private String reviewComment;

    @Column(name = "review_star", nullable = false)
    private Double reviewStar;

}