package com.acs.elearn.search.controller;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class CheckController {

    @GetMapping(path = "/")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> get() {
        // test
        return ResponseHandler.generateResponse("ELearn-search Health Check Success.Test", HttpStatus.OK, null);
    }
}
