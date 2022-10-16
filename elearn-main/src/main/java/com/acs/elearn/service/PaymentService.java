package com.acs.elearn.service;

import com.acs.elearn.dao.model.Transaction;



public interface PaymentService {
    Transaction createTransaction(String cartId);
}
