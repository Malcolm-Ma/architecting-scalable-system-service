package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.CommodityRanking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRankingRepository extends JpaRepository<CommodityRanking, String> {
}

