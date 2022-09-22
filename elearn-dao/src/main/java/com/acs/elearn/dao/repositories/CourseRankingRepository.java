package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.CourseRanking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRankingRepository extends JpaRepository<CourseRanking, String> {
}

