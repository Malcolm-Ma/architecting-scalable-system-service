package com.acs.elearn.dao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "user_id")
    private User merchant;

    @ManyToMany
    @JoinTable(
            name = "transaction_commodity",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "commodity_id")
    )
    private List<Commodity> commodityList;

    @Column(name = "commodity_real_price", nullable = false, updatable = false)
    private Integer commodityRealPrice;

    @CreatedDate
    @Column(name = "transaction_time", nullable = false, updatable = false)
    private LocalDateTime transactionTime;

}
