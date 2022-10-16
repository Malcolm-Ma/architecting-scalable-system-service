package com.acs.elearn.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddReviewRequest {
    @NotNull
    private String userId;

    @NotNull
    private  String CommodityId;

    @NotNull
    private String reviewComment;

    @ApiModelProperty(value = "default is 3", required = true)
    @NotNull
    @Max(5)
    @Min(0)
    private Double reviewStar = 3.0;
}
