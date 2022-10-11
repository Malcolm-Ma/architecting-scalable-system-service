package com.acs.elearn.common.vo;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@Data
public class CommoditySearchRequest {

    @NotNull
    private String keyword;

    private Integer pageSize;

    private Integer PageNum;

    private Double star;

    private Integer price;

}
