package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
@Data
public class ShoppingCart {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //generate 32length UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name = "cart_id", nullable = false, length = 32)
    private String cartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_id_commodity",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_commodity")
    )
    @JsonIgnoreProperties({"shopping_cart_list"})
    private List<Commodity> cartCommodity;

}
