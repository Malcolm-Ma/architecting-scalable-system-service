package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.CourseInformation;
import com.acs.elearn.dao.model.ReviewInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewInformationRepository extends JpaRepository<ReviewInformation, String> {
}