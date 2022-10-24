package com.acs.elearn.vo;

import lombok.Data;

@Data
public class CourseCreateRequest {

    String commodityId;

    String  courseName;

    String courseResource;

    int courseSequence;
}
