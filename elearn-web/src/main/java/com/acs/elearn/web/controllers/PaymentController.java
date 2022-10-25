package com.acs.elearn.web.controllers;


import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.Transaction;
import com.acs.elearn.service.PaymentService;
import com.acs.elearn.service.impl.PaymentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/pay")
public class PaymentController {

    final PaymentService paymentService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/transaction")
    @ResponseBody
    ResponseEntity<ResponseModel<Transaction>> createTransaction(@NotNull @RequestParam String cartId) {
        try {
            Transaction res = paymentService.createTransaction(cartId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }
}
