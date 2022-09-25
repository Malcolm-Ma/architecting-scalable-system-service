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

    @Column(name = "commodity_id", nullable = false)
    private String commodityId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @CreatedDate
    @Column(name = "review_create_time", nullable = false, updatable = false)
    private LocalDateTime reviewCreateTime;

    @Column(name = "review_comment",columnDefinition="TEXT")
    private String reviewComment;

    @Column(name = "review_decision", nullable = false)
    private boolean reviewDecision;

    public CommodityReviewRecord() {}

}