package com.acs.elearn.dao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, length = 32)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role userRole;

    @ManyToMany
    @JoinTable(
            name = "user_id_hobby",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_hobby")
    )
    private List<Tag> tagList;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart userShoppingCart;

    @Column(name = "user_username", nullable = false)
    private String userUsername;

    @Column(name = "user_age", nullable = false)
    private int userAge;

    @Column(name = "user_firstname", nullable = false)
    private String userFirstname;

    @Column(name = "user_lastname", nullable = false)
    private String userLastname;

    @CreatedDate
    @Column(name = "user_created_time", nullable = false, updatable = false)
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

    @Column(name = "user_merchant_level")
    private int userMerchantLevel;

    @Column(name = "user_consumer_level")
    private int userConsumerLevel;
}
