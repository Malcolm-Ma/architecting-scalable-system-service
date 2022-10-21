package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.CourseInformation;
import com.acs.elearn.vo.CommodityCreateRequest;
import com.acs.elearn.vo.CourseCreateRequest;

public interface CourseService {
    CourseInformation createCourse(CourseCreateRequest request);
    String updateCourse(CourseInformation course) throws Exception;
    String deleteCourse(CourseInformation course) throws Exception;
    CourseInformation getCourseInfo(String courseId);
}
