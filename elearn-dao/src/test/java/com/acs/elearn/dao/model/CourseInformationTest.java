package com.acs.elearn.dao.model;

import com.acs.elearn.dao.repositories.CourseInformationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootConfiguration
//@RunWith(SpringRunner.class)
@SpringBootTest()
class CourseInformationTest {

    @Test
    public void addCourseInformation() {
        CourseInformation testCase1 = new CourseInformation();

        testCase1.setCourseResource("haha");
        testCase1.setCourseSequence(2);
        System.out.println(testCase1);
//        assertEquals(testCase1,Cours);

    }

    @Test
    public void CourseInformation() {

    }

}