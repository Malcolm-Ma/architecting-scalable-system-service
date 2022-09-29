package com.acs.elearn.dao.model;

import com.acs.elearn.dao.dto.CommodityDto;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commodity")
@Data
public class Commodity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commodity_id", nullable = false, length = 32)
    private String commodityId;

    @Column(name = "commodity_name", nullable = false,length = 128)
    private String commodityName;

    @Lob
    @Column(name = "commodity_introduction", nullable = false, columnDefinition="TEXT")
    private String commodityIntroduction;

    @Column(name = "commodity_star", nullable = false)
    private BigInteger commodityStar;

    @Column(name = "commodity_price", nullable = false)
    private Double commodityPrice;

    @Column(name = "commodity_discount", nullable = false)
    private Double commodityDiscount;

    @Column(name = "commodity_sold_cnt", nullable = false)
    private Integer commoditySoldCnt;

    @Column(name = "commodity_status", nullable = false)
    private Integer commodityStatus;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "user_id")
    private User merchant;

    @CreatedDate
    @Column(name= "commodity_create_time", nullable = false, updatable = false)
    private LocalDateTime commodityCreateTime;

    @LastModifiedDate
    @Column(name = "commodity_update_time")
    private LocalDateTime commodityUpdateTime;

}
