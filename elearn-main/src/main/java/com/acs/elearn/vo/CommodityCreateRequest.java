package com.acs.elearn.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CommodityCreateRequest {
    @NotNull
    private String userId;

    @NotNull
    private List<String> courseId;

    @NotNull
    private String commodityCover;

    private String commodityName;

    private String commodityIntroduction;

    private Double commodityPrice;

    private Double commodityDiscount;

    private Integer commodityStatus;
}
