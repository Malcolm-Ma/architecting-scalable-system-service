package com.acs.elearn.vo;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CommodityCreateRequest {
    @NotNull
    private String userId;

    private List<String> courseId;

    private String commodityName;

    private String commodityIntroduction;

    private Double commodityPrice;

    private Double commodityDiscount;

    private Integer commodityStatus;
}
