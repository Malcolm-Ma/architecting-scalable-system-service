package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "commodity_ranking")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "commodity_rank")
public class CommodityRanking {

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

    @OneToOne
    @JoinColumn( name = "commodity_id")
    private Commodity commodity;

}
