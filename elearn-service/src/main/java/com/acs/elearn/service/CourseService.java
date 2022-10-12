package com.acs.elearn.service;

import com.acs.elearn.dao.model.CourseInformation;

public interface CourseService {
    CourseInformation getCourseInfo(String courseId);
}
