package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.CourseInformation;
import com.acs.elearn.service.CourseService;
import com.acs.elearn.vo.CommodityCreateRequest;
import com.acs.elearn.vo.CourseCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {
    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping(path = "/create_course", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ResponseModel<CourseInformation>> createCommodity(CourseCreateRequest request) {
        CourseInformation res = courseService.createCourse(request);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @PostMapping(path = "/update_course", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseModel<String>> updateCommodity(CourseInformation course) throws Exception {
        String res = courseService.updateCourse(course);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @PostMapping(path = "/delete_course", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ResponseModel<String>> deleteCommodity(CourseInformation course) throws Exception {
        String res = courseService.deleteCourse(course);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }
    @GetMapping(path = "/get_course_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel<CourseInformation>> getCourseInfo(String courseId) {
        CourseInformation res = courseService.getCourseInfo(courseId);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }
}
