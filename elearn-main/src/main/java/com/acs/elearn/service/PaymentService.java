package com.acs.elearn.service;

import com.acs.elearn.dao.model.Transaction;



public interface PaymentService {
    public Transaction createTransaction(String cartId);
}
