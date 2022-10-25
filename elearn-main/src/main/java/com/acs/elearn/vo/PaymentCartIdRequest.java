package com.acs.elearn.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentCartIdRequest {
    @NotNull
    public String cartId;
}
