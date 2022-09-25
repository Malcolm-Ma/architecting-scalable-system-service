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
    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private Role userRole;

    @ManyToMany
    @JoinTable(
            name = "user_id_hobby",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_hobby")
    )
    private List<Tag> tagList;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "user_id")
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
    @Column(name = "user_created_timestamp", nullable = false, updatable = false)
    private LocalDateTime userCreatedTimestamp;

    @Column(name = "user_email_verified", nullable = false)
    private int userEmailVerified;

    @Column(name = "user_enabled", nullable = false)
    private int userEnabled;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "user_contact")
    private String userContact;

    @Column(name = "user_introduction")
    private String userIntroduction;

    @Column(name = "user_merchant_level")
    private int userMerchantLevel;

    @Column(name = "user_consumer_level")
    private int userConsumerLevel;
}
