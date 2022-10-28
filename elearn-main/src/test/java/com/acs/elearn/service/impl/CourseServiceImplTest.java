package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.*;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.CourseInformationRepository;
import com.acs.elearn.vo.CourseCreateRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTest {

    @Mock
    private CourseInformationRepository mockCourseInformationRepository;
    @Mock
    private CommodityRepository mockCommodityRepository;

    private CourseServiceImpl courseServiceImplUnderTest;

    @Before
    public void setUp() {
        courseServiceImplUnderTest = new CourseServiceImpl(mockCourseInformationRepository, mockCommodityRepository);
    }





    @Test
    public void testUpdateCourse_CourseInformationRepositoryFindByCourseIdReturnsNull() {
        // Setup
        final CourseInformation course = new CourseInformation();
        course.setCourseId("courseId");
        final Commodity commodity = new Commodity();
        commodity.setCommodityId("commodityId");
        final CommodityRanking commodityRanking = new CommodityRanking();
        commodityRanking.setCommodityRank(new BigInteger("100"));
        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity.setCommodityRanking(commodityRanking);
        final User publishedBy = new User();
        publishedBy.setUserId("userId");
        publishedBy.setPurchasedCommodities(List.of(new Commodity()));
        final Tag tag = new Tag();
        publishedBy.setTagList(List.of(tag));
        commodity.setPublishedBy(publishedBy);
        course.setCommodity(commodity);
        course.setCourseName("courseName");
        course.setCourseResource("courseResource");
        course.setCourseSequence(0);

        when(mockCourseInformationRepository.findByCourseId("courseId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> courseServiceImplUnderTest.updateCourse(course)).isInstanceOf(Exception.class);
    }


    @Test
    public void testDeleteCourse_CourseInformationRepositoryFindByCourseIdReturnsNull() {
        // Setup
        final CourseInformation course = new CourseInformation();
        course.setCourseId("courseId");
        final Commodity commodity = new Commodity();
        commodity.setCommodityId("commodityId");
        final CommodityRanking commodityRanking = new CommodityRanking();
        commodityRanking.setCommodityRank(new BigInteger("100"));
        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity.setCommodityRanking(commodityRanking);
        final User publishedBy = new User();
        publishedBy.setUserId("userId");
        publishedBy.setPurchasedCommodities(List.of(new Commodity()));
        final Tag tag = new Tag();
        publishedBy.setTagList(List.of(tag));
        commodity.setPublishedBy(publishedBy);
        course.setCommodity(commodity);
        course.setCourseName("courseName");
        course.setCourseResource("courseResource");
        course.setCourseSequence(0);

        when(mockCourseInformationRepository.findByCourseId("courseId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> courseServiceImplUnderTest.deleteCourse(course)).isInstanceOf(Exception.class);
    }



    @Test
    public void testGetCourseInfo() {
        // Setup
        final CourseInformation expectedResult = new CourseInformation();
        expectedResult.setCourseId("courseId");
        final Commodity commodity = new Commodity();
        commodity.setCommodityId("commodityId");
        final CommodityRanking commodityRanking = new CommodityRanking();
        commodityRanking.setCommodityRank(new BigInteger("100"));
        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity.setCommodityRanking(commodityRanking);
        final User publishedBy = new User();
        publishedBy.setUserId("userId");
        publishedBy.setPurchasedCommodities(List.of(new Commodity()));
        final Tag tag = new Tag();
        publishedBy.setTagList(List.of(tag));
        commodity.setPublishedBy(publishedBy);
        expectedResult.setCommodity(commodity);
        expectedResult.setCourseName("courseName");
        expectedResult.setCourseResource("courseResource");
        expectedResult.setCourseSequence(0);

        // Configure CourseInformationRepository.findByCourseId(...).
        final CourseInformation courseInformation = new CourseInformation();
        courseInformation.setCourseId("courseId");
        final Commodity commodity1 = new Commodity();
        commodity1.setCommodityId("commodityId");
        final CommodityRanking commodityRanking1 = new CommodityRanking();
        commodityRanking1.setCommodityRank(new BigInteger("100"));
        commodityRanking1.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking1.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking1.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity1.setCommodityRanking(commodityRanking1);
        final User publishedBy1 = new User();
        publishedBy1.setUserId("userId");
        publishedBy1.setPurchasedCommodities(List.of(new Commodity()));
        final Tag tag1 = new Tag();
        publishedBy1.setTagList(List.of(tag1));
        commodity1.setPublishedBy(publishedBy1);
        courseInformation.setCommodity(commodity1);
        courseInformation.setCourseName("courseName");
        courseInformation.setCourseResource("courseResource");
        courseInformation.setCourseSequence(0);
        when(mockCourseInformationRepository.findByCourseId("courseId")).thenReturn(courseInformation);

        // Run the test
        final CourseInformation result = courseServiceImplUnderTest.getCourseInfo("courseId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
