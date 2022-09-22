package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.CourseInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInformationRepository extends JpaRepository<CourseInformation, String>{
    public CourseInformation findByName(String courseName);
}
