package com.acs.elearn.dao.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaction")
@Data
@EntityListeners(AuditingEntityListener.class) // date
public class Transaction {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //generate 32length UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name = "transaction_id", nullable = false, length = 32)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "transaction_commodity",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "commodity_id")
    )
    private List<Commodity> commodityList;

    @Column(name = "commodity_real_price", nullable = false, updatable = false)
    private Double commodityRealPrice;

    @CreatedDate
    @Column(name = "transaction_time", nullable = false, updatable = false)
    private LocalDateTime transactionTime;

}
