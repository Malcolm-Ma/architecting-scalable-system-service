package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "user_id")
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

    @ManyToMany
    @JoinTable(
            name = "user_id_hobby",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_hobby")
    )
    private List<Tag> tagList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserActionTracing> userActionTracingList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ReplyRecord> replyRecordList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CourseUserProgress> courseProgresses;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Review> reviewList;

    @OneToMany(mappedBy = "publishedBy")
    private List<Commodity> publishedCommodities;

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

    @Column(name = "keycloak_id")
    private String keycloakId;

    @Lob
    @Column(name = "user_introduction",columnDefinition="TEXT")
    private String userIntroduction;
}
