package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.CourseUserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseUserProgressRepository extends JpaRepository<CourseUserProgress, String> {
}