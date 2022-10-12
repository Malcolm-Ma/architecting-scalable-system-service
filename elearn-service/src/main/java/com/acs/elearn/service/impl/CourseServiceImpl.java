package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.CourseInformation;
import com.acs.elearn.dao.repositories.CourseInformationRepository;
import com.acs.elearn.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseInformationRepository courseInformationRepository;

    public CourseInformation getCourseInfo(String courseId){
        return courseInformationRepository.findByCourseId(courseId);
    }
}
