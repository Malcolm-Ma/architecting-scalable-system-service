package com.acs.elearn.vo;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@Data
public class CommoditySearchRequest {

    @NotNull
    private String keyword;

    @NotNull
    private Integer pageSize;

    @NotNull
    private Integer PageNum;

    private Double star;

    private Double price;

    public CommoditySearchRequest() {}

}
