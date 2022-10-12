package com.acs.elearn.dao.model;

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

    @OneToOne(mappedBy = "userShoppingCart")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_id_commodity",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_commodity")
    )
    private List<Commodity> cartCommodity;

    @Column(name = "cart_item_quantity", nullable = false)
    private int cartItemQuantity;
}
