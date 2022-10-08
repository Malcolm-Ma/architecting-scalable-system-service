package com.acs.elearn.dao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "commodity_review_record")
@Data
public class CommodityReviewRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id", nullable = false, length = 32)
    private String reviewId;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(name = "commodity_review_create_time", nullable = false, updatable = false)
    private LocalDateTime commodityReviewCreateTime;

    @Column(name = "commodity_review_comment",columnDefinition="TEXT")
    private String commodityReviewComment;

    @Column(name = "commodity_review_decision", nullable = false)
    private boolean commodityReviewDecision;

    @Column(name = "review_star", nullable = false)
    private Double reviewStar;

}