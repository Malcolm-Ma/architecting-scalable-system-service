package com.acs.elearn.dao.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CommodityDto {
    private @NotNull String name;
    private @NotNull float price;

    public CommodityDto() {}

    public CommodityDto(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
