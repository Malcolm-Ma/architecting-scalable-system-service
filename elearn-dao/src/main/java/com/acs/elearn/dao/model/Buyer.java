package com.acs.elearn.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "buyer")
@Data
@DiscriminatorValue("1")
public class Buyer extends User {
    @Column(name = "user_consumer_level")
    private int userConsumerLevel;
}
