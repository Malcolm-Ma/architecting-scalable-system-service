package com.acs.elearn.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommodityIdUserIdRequest {

    @NotNull
    private String CommodityId;

    @NotNull
    private String UserId;
}
