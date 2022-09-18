package com.acs.elearn.dao.model;

import com.acs.elearn.dao.dto.CommodityDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "commodity")
public class Commodity implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToMany(mappedBy = "commodityList")
    private List<Order> allOrders;

    private String name;

    private float price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Commodity() {}

    public Commodity(CommodityDto dto) {
        this.price = dto.getPrice();
        this.name = dto.getName();
    }

}
