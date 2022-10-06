package com.acs.elearn.dao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "review_information")
@Data
public class ReviewInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id", nullable = false, length = 32)
    private String reviewId;

    @Column(name = "commodity_id", nullable = false)
    private String commodityId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "review_star", nullable = false)
    private String reviewStar;

    @Column(name = "review_comment", nullable = false, columnDefinition="TEXT")
    private String reviewComment;

    @CreatedDate
    @Column(name = "review_create_time", nullable = false, updatable = false)
    private LocalDateTime reviewCreateTime;
}
