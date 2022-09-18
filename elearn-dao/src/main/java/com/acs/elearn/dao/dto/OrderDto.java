package com.acs.elearn.dao.dto;

import com.sun.istack.NotNull;

public class OrderDto {

    private @NotNull float totalPrice;

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDto() {}

    public OrderDto(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
