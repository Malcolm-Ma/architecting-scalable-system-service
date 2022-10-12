package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    Buyer findBuyerByUserId(String UserId);
}
