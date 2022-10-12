package com.acs.elearn.web.controllers;

import com.acs.elearn.dao.model.CourseInformation;
import com.acs.elearn.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping(path = "/get_course_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseInformation getCourseInfo(String courseId) {
        return courseService.getCourseInfo(courseId);
    }
}
