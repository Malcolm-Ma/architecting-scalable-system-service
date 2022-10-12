package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
