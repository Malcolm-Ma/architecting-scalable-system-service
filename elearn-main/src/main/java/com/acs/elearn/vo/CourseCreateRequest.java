package com.acs.elearn.vo;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class CourseCreateRequest {
    @NotNull
    String commodityId;

    String  courseName;

    String courseResource;

    int courseSequence;
}
