package com.acs.elearn.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateReviewRequest {
    @NotNull
    private String reviewId;

    @NotNull
    private String reviewComment;

    @NotNull
    @Max(5)
    @Min(0)
    private Double reviewStar;
}
