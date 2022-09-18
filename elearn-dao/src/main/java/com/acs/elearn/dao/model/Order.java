package com.acs.elearn.dao.model;

import com.acs.elearn.dao.dto.OrderDto;
import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "created_time")
    private Date createdTimestamp;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    @ManyToMany
    @JoinTable(
            name = "order_commodity",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "commodity_id")
    )
    private List<Commodity> commodityList;

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public String getId() {
        return id;
    }

    public Order() {
        this.createdTimestamp = new Date();
    }

    public Order(OrderDto orderDto) {
        this.totalPrice = orderDto.getTotalPrice();
        this.createdTimestamp = new Date();
    }

}
