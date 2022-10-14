package com.acs.elearn.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CommoditySearchRequest {

    @NotNull
    @ApiModelProperty(value = "Search keywords", required = true)
    private String keyword;

    @ApiModelProperty(value = "Size of each page, default is 10", required = true)
    @Min(1)
    @Max(10000)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "Required page number, default is 1", required = true)
    @Min(1)
    private Integer PageNum = 1;

    private Double star;

    private Double price;

}
