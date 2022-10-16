package com.acs.elearn.dao.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class) // date
@Data
public class User {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //generate 32length UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name = "user_id", nullable = false, length = 32)
    private String userId;

    @ManyToMany
    @JoinTable(
            name = "buyer_commodity",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "commodity_id")
    )
    private List<Commodity> purchasedCommodities;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role userRole;

    @OneToMany(mappedBy = "user")
    private List<UserActionTracing> userActionTracingList;

    @OneToMany(mappedBy = "user")
    private List<ReplyRecord> replyRecordList;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "user")
    private List<CourseUserProgress> courseProgresses;

    @OneToMany(mappedBy = "user")
    private List<CommodityReviewRecord> commodityReviewRecordList;

    @OneToMany(mappedBy = "publishedBy")
    private List<Commodity> publishedCommodities;

    @ManyToMany
    @JoinTable(
            name = "user_id_hobby",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_hobby")
    )
    private List<Tag> tagList;

    @OneToOne(mappedBy = "user")
    private ShoppingCart userShoppingCart;

    @Column(name = "user_username", nullable = false)
    private String userUsername;

    @Column(name = "user_age")
    private Integer userAge = 12;

    @Column(name = "user_firstname")
    private String userFirstname;

    @Column(name = "user_lastname")
    private String userLastname;

    @CreatedDate
//    @Column(name = "user_created_time", nullable = false, updatable = false)
    @Column(name = "user_created_time",  updatable = false)
    private LocalDateTime userCreatedTime;

    @Column(name = "user_email_verified", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean userEmailVerified;

    @Column(name = "user_enabled", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean userEnabled;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "user_contact")
    private String userContact;

    @Lob
    @Column(name = "user_introduction",columnDefinition="TEXT")
    private String userIntroduction;
}
