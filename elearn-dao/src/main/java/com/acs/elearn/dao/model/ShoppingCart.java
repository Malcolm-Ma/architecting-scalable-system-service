package com.acs.elearn.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id", nullable = false)
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
