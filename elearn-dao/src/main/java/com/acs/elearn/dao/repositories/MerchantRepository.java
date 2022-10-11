package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    Merchant getMerchantByUserId(String userId);
}
