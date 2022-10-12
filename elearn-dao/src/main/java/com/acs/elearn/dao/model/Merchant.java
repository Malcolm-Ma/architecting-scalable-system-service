//package com.acs.elearn.dao.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "merchant")
//@Data
//@DiscriminatorValue("2")
//public class Merchant extends User {
//
//    @Column(name = "user_merchant_level")
//    private int userMerchantLevel;
//
//    @OneToMany(mappedBy = "publishedBy")
//    private List<Commodity> publishedCommodities;
//}
