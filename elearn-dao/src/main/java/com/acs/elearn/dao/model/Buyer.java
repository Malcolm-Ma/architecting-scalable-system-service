//package com.acs.elearn.dao.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "buyer")
//@Data
//@DiscriminatorValue("1")
//public class Buyer extends User {
//
//    @Column(name = "user_consumer_level")
//    private int userConsumerLevel;
//
//    @ManyToMany
//    @JoinTable(
//            name = "buyer_commodity",
//            joinColumns = @JoinColumn(name = "buyer_id"),
//            inverseJoinColumns = @JoinColumn(name = "commodity_id")
//    )
//    private List<Commodity> purchasedCommodities;
//}
