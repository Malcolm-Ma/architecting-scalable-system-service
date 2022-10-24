package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.CourseInformation;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.CourseInformationRepository;
import com.acs.elearn.service.CourseService;
import com.acs.elearn.vo.CourseCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    final CourseInformationRepository courseInformationRepository;
    final CommodityRepository commodityRepository;

    public CourseServiceImpl(
            CourseInformationRepository courseInformationRepository,
            CommodityRepository commodityRepository
    ) {
        this.courseInformationRepository = courseInformationRepository;
        this.commodityRepository = commodityRepository;
    }

    @Override
    public CourseInformation createCourse(CourseCreateRequest request){
        Commodity commodity = commodityRepository.findByCommodityId(request.getCommodityId());
        CourseInformation courseInformation = new CourseInformation();
        courseInformation.setCommodity(commodity);
        courseInformation.setCourseName(request.getCourseName());
        courseInformation.setCourseResource(request.getCourseResource());
        courseInformation.setCourseSequence(request.getCourseSequence());
        courseInformationRepository.save(courseInformation);
        return courseInformation;
    }
    @Override
    public String updateCourse(CourseInformation course) throws Exception{
        CourseInformation curCourse = courseInformationRepository.findByCourseId(course.getCourseId());
        if (curCourse != null) {
            BeanUtil.copyProperties(course, curCourse, CopyOptions.create().setIgnoreNullValue(true));
            courseInformationRepository.save(curCourse);
            return "Add successfully";
        }
        else {
            throw new Exception("commodity is not existing");
        }
    }
    @Override
    public String deleteCourse(CourseInformation course) throws Exception{
        CourseInformation curCourse = courseInformationRepository.findByCourseId(course.getCourseId());
        if(curCourse != null){
            courseInformationRepository.delete(curCourse);
            return "Delete successfully";
        } else{
            throw new Exception("Course is not existing");
        }
    }
    @Override
    public CourseInformation getCourseInfo(String courseId){
        return courseInformationRepository.findByCourseId(courseId);
    }


}
