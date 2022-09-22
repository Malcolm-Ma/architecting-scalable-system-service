package com.acs.elearn.dao.model;

import com.sun.xml.bind.v2.TODO;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "course_ranking")
@Data
public class CourseRanking {

    @Id
    @Column(name = "commodity_rank", nullable = false)
    private BigInteger commodityRank;

    @Column(name = "commodity_view_user_cnt", nullable = false)
    private BigInteger commodityViewUserCnt;

    @Column(name = "commodity_view_page_cnt", nullable = false)
    private BigInteger commodityViewPageCnt;

    @Column(name = "commodity_exposure_user_cnt", nullable = false)
    private BigInteger CommodityExposureUserCnt;

    @Column(name = "commodity_exposure_page_cnt", nullable = false)
    private BigInteger commodityExposureUserCnt ;

//    @OneToOne()
//    @JoinTable(name = "Commodity", joinColumns = )
//    @JoinColumn(name = "commodity_sold_cnt")
//    TODO 外键匹配
//    @Column(name = "commodity_sold_cnt", nullable = false)
//    private BigInteger commoditySoldCnt;
//
//    @Column(name = "commodity_star", nullable = false)
//    private BigInteger commodityStar;
//
//    @Column(name = "commodity_price", nullable = false)
//    private Double commodityPrice;
//
//    @Column(name = "commodity_discount", nullable = false)
//    private Double commodityDiscount;
//
//    @Column(name = "commodity_sold_cnt", nullable = false)
//    private BigInteger commoditySoldCnt;
}
