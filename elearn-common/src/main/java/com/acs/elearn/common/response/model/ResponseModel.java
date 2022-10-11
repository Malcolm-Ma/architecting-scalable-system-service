package com.acs.elearn.common.response.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel<T> {
    private String message;
    private int status;
    private T data;
}
