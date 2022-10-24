package com.acs.elearn.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommodityIdUserIdRequest {

    @NotNull
    private String commodityId;

    @NotNull
    private String userId;
}
