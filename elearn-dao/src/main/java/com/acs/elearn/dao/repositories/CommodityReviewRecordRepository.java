package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.CommodityReviewRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityReviewRecordRepository extends JpaRepository<CommodityReviewRecord, String> {
}
