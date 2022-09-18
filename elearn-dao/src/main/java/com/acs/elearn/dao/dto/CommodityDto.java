package com.acs.elearn.dao.dto;

import com.sun.istack.NotNull;

public class CommodityDto {
    private @NotNull String name;
    private @NotNull float price;

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

    public CommodityDto() {}

    public CommodityDto(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
