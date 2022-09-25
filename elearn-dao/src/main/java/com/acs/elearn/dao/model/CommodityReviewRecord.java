package com.acs.elearn.dao.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "commodity_review_record")
@Data
public class CommodityReviewRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id", nullable = false)
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

}