package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.*;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.ReviewRepository;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.vo.AddReviewRequest;
import com.acs.elearn.vo.UpdateReviewRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceImplTest {

    @Mock
    private CommodityRepository mockCommodityRepository;
    @Mock
    private ReviewRepository mockReviewRepository;
    @Mock
    private UserRepository mockUserRepository;

    private ReviewServiceImpl reviewServiceImplUnderTest;

    @Before
    public void setUp() {
        reviewServiceImplUnderTest = new ReviewServiceImpl(mockCommodityRepository, mockReviewRepository,
                mockUserRepository);
    }



    @Test
    public void testAddReview_UserRepositoryReturnsNull() {
        // Setup
        final AddReviewRequest requestBody = new AddReviewRequest();
        requestBody.setUserId("userId");
        requestBody.setCommodityId("CommodityId");
        requestBody.setReviewComment("reviewComment");
        requestBody.setReviewStar(0.0);

        when(mockUserRepository.findUserByUserId("userId")).thenReturn(null);

        // Configure CommodityRepository.findByCommodityId(...).
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
        tag.setTagId("tagId");
        tag.setTagName("tagName");
        tag.setTagInfo("tagInfo");
        publishedBy.setTagList(List.of(tag));
        final UserActionTracing userActionTracing = new UserActionTracing();
        publishedBy.setUserActionTracingList(List.of(userActionTracing));
        commodity.setPublishedBy(publishedBy);
        commodity.setCommodityStar(0.0);
        when(mockCommodityRepository.findByCommodityId("CommodityId")).thenReturn(commodity);

        // Run the test
        assertThatThrownBy(() -> reviewServiceImplUnderTest.addReview(requestBody)).isInstanceOf(Exception.class);
    }


    @Test
    public void testDeleteReview() throws Exception {
        // Setup
        // Configure ReviewRepository.findReviewByReviewId(...).
        final Review review = new Review();
        review.setReviewId("reviewId");
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
        commodity.setPublishedBy(publishedBy);
        commodity.setCommodityStar(0.0);
        review.setCommodity(commodity);
        final User user = new User();
        user.setUserId("userId");
        final Commodity commodity1 = new Commodity();
        commodity1.setCommodityId("commodityId");
        final CommodityRanking commodityRanking1 = new CommodityRanking();
        commodityRanking1.setCommodityRank(new BigInteger("100"));
        commodityRanking1.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking1.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking1.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity1.setCommodityRanking(commodityRanking1);
        commodity1.setPublishedBy(new User());
        commodity1.setCommodityStar(0.0);
        user.setPurchasedCommodities(List.of(commodity1));
        review.setUser(user);
        review.setReviewComment("reviewComment");
        review.setReviewStar(0.0);
        when(mockReviewRepository.findReviewByReviewId("reviewId")).thenReturn(review);

        // Run the test
        final String result = reviewServiceImplUnderTest.deleteReview("reviewId");

        // Verify the results
        assertThat(result).isEqualTo("Delete successfully");
        verify(mockReviewRepository).deleteById("reviewId");
    }

    @Test
    public void testDeleteReview_ReviewRepositoryFindReviewByReviewIdReturnsNull() {
        // Setup
        when(mockReviewRepository.findReviewByReviewId("reviewId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> reviewServiceImplUnderTest.deleteReview("reviewId")).isInstanceOf(Exception.class);
    }



    @Test
    public void testUpdateReview_ReviewRepositoryFindReviewByReviewIdReturnsNull() {
        // Setup
        final UpdateReviewRequest requestBody = new UpdateReviewRequest();
        requestBody.setReviewId("reviewId");
        requestBody.setReviewComment("reviewComment");
        requestBody.setReviewStar(0.0);

        when(mockReviewRepository.findReviewByReviewId("reviewId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> reviewServiceImplUnderTest.updateReview(requestBody)).isInstanceOf(Exception.class);
    }


    @Test
    public void testDisplayReviewList_CommodityRepositoryReturnsNull() {
        // Setup
        when(mockCommodityRepository.findByCommodityId("commodityId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> reviewServiceImplUnderTest.displayReviewList("commodityId"))
                .isInstanceOf(Exception.class);
    }
//
//    @Test
//    public void testDisplayReviewList_ReviewRepositoryReturnsNoItems() throws Exception {
//        // Setup
//        // Configure CommodityRepository.findByCommodityId(...).
//        final Commodity commodity = new Commodity();
//        commodity.setCommodityId("commodityId");
//        final CommodityRanking commodityRanking = new CommodityRanking();
//        commodityRanking.setCommodityRank(new BigInteger("100"));
//        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
//        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
//        commodity.setCommodityRanking(commodityRanking);
//        final User publishedBy = new User();
//        publishedBy.setUserId("userId");
//        publishedBy.setPurchasedCommodities(List.of(new Commodity()));
//        final Tag tag = new Tag();
//        tag.setTagId("tagId");
//        tag.setTagName("tagName");
//        tag.setTagInfo("tagInfo");
//        publishedBy.setTagList(List.of(tag));
//        final UserActionTracing userActionTracing = new UserActionTracing();
//        publishedBy.setUserActionTracingList(List.of(userActionTracing));
//        commodity.setPublishedBy(publishedBy);
//        commodity.setCommodityStar(0.0);
////        when(mockCommodityRepository.findByCommodityId("commodityId")).thenReturn(commodity);
////
////        when(mockReviewRepository.findReviewsByCommodity(new Commodity())).thenReturn(Collections.emptyList());
//
//        // Run the test
////        final List<Review> result = reviewServiceImplUnderTest.displayReviewList("commodityId");
//
//        // Verify the results
////        assertThat(result).isEqualTo(Collections.emptyList());
//    }


}
