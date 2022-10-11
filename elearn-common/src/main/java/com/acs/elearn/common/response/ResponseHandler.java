package com.acs.elearn.common.response;

import com.acs.elearn.common.response.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static <T> ResponseEntity<ResponseModel<T>>
    generateResponse(String message, HttpStatus status, T responseObj) {

//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("message", message);
//        map.put("status", status.value());
//        map.put("data", responseObj);

        ResponseModel<T> responseData = new ResponseModel<T>(message, status.value(), responseObj);

        return new ResponseEntity<ResponseModel<T>>(responseData,status);
    }
}
