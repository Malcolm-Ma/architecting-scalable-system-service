package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commodity")
@Data
@EntityListeners(AuditingEntityListener.class) // date
public class Commodity implements Serializable {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //generate 32length UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name = "commodity_id", nullable = false)
    private String commodityId;

    @ManyToOne
    @JoinColumn(name = "published_by", referencedColumnName = "user_id")
    private User publishedBy;

    @OneToMany(mappedBy = "commodity")
    private List<UserActionTracing> userActionTracingList;

    @OneToMany(mappedBy = "commodity")
    private List<CourseInformation> courseList;

    @OneToMany(mappedBy = "commodity")
    private List<Review> reviewList;

    @ManyToMany(mappedBy = "commodityList")
    private List<Transaction> transactionList;

    @ManyToMany(mappedBy = "purchasedCommodities")
    private List<User> userList;

    @ManyToMany(mappedBy = "cartCommodity")
    @JsonIgnore
    private List<ShoppingCart> shoppingCartList;

    @Column(name = "commodity_name", nullable = false,length = 128)
    private String commodityName;

    @Lob
    @Column(name = "commodity_introduction", nullable = false, columnDefinition="TEXT")
    private String commodityIntroduction;

    @Column(name = "commodity_star", nullable = false)
    private Double commodityStar;

    @Column(name = "commodity_price", nullable = false)
    private Double commodityPrice;

    @Column(name = "commodity_discount", nullable = false)
    private Double commodityDiscount;

    @Column(name = "commodity_sold_cnt", nullable = false)
    private Integer commoditySoldCnt;

    @Column(name = "commodity_status", nullable = false)
    private Integer commodityStatus;

    @CreatedDate
    @Column(name= "commodity_create_time", nullable = false, updatable = false)
    private Date commodityCreateTime;

    @LastModifiedDate
    @Column(name = "commodity_update_time")
    private Date commodityUpdateTime;

    @Column(name = "commodity_cover", nullable = false)
    private String commodityCover;

}
