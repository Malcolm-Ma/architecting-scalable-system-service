package com.acs.elearn.common.response;

import com.acs.elearn.common.response.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {
    public static <T> ResponseEntity<ResponseModel<T>>
    generateResponse(String message, HttpStatus status, T responseObj) {

        ResponseModel<T> responseData = new ResponseModel<T>(message, status.value(), responseObj);

        return new ResponseEntity<ResponseModel<T>>(responseData,status);
    }
}
