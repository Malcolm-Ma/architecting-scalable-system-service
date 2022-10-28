package com.acs.elearn.service.impl;

import com.acs.elearn.common.exception.UserNullException;
import com.acs.elearn.dao.model.*;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.vo.AddUserInfoRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private CartServiceImpl mockCartService;
    @Mock
    private UserRepository mockUserRepository;

    private UserServiceImpl userServiceImplUnderTest;

    @Before
    public void setUp() {
        userServiceImplUnderTest = new UserServiceImpl(mockCartService, mockUserRepository);
    }

    @Test
    public void testGetUserInfo() throws Exception {
        // Setup
        final User expectedResult = new User();
        expectedResult.setUserId("userId");
        final Commodity commodity = new Commodity();
        commodity.setCommodityId("commodityId");
        final CommodityRanking commodityRanking = new CommodityRanking();
        commodityRanking.setCommodityRank(new BigInteger("100"));
        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity.setCommodityRanking(commodityRanking);
        final UserActionTracing userActionTracing = new UserActionTracing();
        userActionTracing.setEventId(0L);
        userActionTracing.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing.setTracingType(0);
        commodity.setUserActionTracingList(List.of(userActionTracing));
        final CourseInformation courseInformation = new CourseInformation();
        commodity.setCourseList(List.of(courseInformation));
        expectedResult.setPurchasedCommodities(List.of(commodity));
        final Commodity commodity1 = new Commodity();
        commodity1.setCommodityId("commodityId");
        final CommodityRanking commodityRanking1 = new CommodityRanking();
        commodityRanking1.setCommodityRank(new BigInteger("100"));
        commodityRanking1.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking1.setCommodityViewPageCnt(new BigInteger("100"));
//        
        commodity1.setCommodityRanking(commodityRanking1);
        final UserActionTracing userActionTracing1 = new UserActionTracing();
        userActionTracing1.setEventId(0L);
        userActionTracing1.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing1.setTracingType(0);
        commodity1.setUserActionTracingList(List.of(userActionTracing1));
        final CourseInformation courseInformation1 = new CourseInformation();
        commodity1.setCourseList(List.of(courseInformation1));
        expectedResult.setPublishedCommodities(List.of(commodity1));
        expectedResult.setUserUsername("userUsername");

        // Configure UserRepository.findUserByUserId(...).
        final User user = new User();
        user.setUserId("userId");
        final Commodity commodity2 = new Commodity();
        commodity2.setCommodityId("commodityId");
        final CommodityRanking commodityRanking2 = new CommodityRanking();
        commodityRanking2.setCommodityRank(new BigInteger("100"));
        commodityRanking2.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking2.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking2.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity2.setCommodityRanking(commodityRanking2);
        final UserActionTracing userActionTracing2 = new UserActionTracing();
        userActionTracing2.setEventId(0L);
        userActionTracing2.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing2.setTracingType(0);
        commodity2.setUserActionTracingList(List.of(userActionTracing2));
        final CourseInformation courseInformation2 = new CourseInformation();
        commodity2.setCourseList(List.of(courseInformation2));
        user.setPurchasedCommodities(List.of(commodity2));
        final Commodity commodity3 = new Commodity();
        commodity3.setCommodityId("commodityId");
        final CommodityRanking commodityRanking3 = new CommodityRanking();
        commodityRanking3.setCommodityRank(new BigInteger("100"));
        commodityRanking3.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking3.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking3.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity3.setCommodityRanking(commodityRanking3);
        final UserActionTracing userActionTracing3 = new UserActionTracing();
        userActionTracing3.setEventId(0L);
        userActionTracing3.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing3.setTracingType(0);
        commodity3.setUserActionTracingList(List.of(userActionTracing3));
        final CourseInformation courseInformation3 = new CourseInformation();
        commodity3.setCourseList(List.of(courseInformation3));
        user.setPublishedCommodities(List.of(commodity3));
        user.setUserUsername("userUsername");
        when(mockUserRepository.findUserByUserId("userId")).thenReturn(user);

        // Run the test
        final User result = userServiceImplUnderTest.getUserInfo("userId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetUserInfo_UserRepositoryReturnsNull() {
        // Setup
        when(mockUserRepository.findUserByUserId("userId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.getUserInfo("userId")).isInstanceOf(UserNullException.class);
    }



    @Test
    public void testGetUserInfoByKcId() throws Exception {
        // Setup
        final User expectedResult = new User();
        expectedResult.setUserId("userId");
        final Commodity commodity = new Commodity();
        commodity.setCommodityId("commodityId");
        final CommodityRanking commodityRanking = new CommodityRanking();
        commodityRanking.setCommodityRank(new BigInteger("100"));
        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity.setCommodityRanking(commodityRanking);
        final UserActionTracing userActionTracing = new UserActionTracing();
        userActionTracing.setEventId(0L);
        userActionTracing.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing.setTracingType(0);
        commodity.setUserActionTracingList(List.of(userActionTracing));
        final CourseInformation courseInformation = new CourseInformation();
        commodity.setCourseList(List.of(courseInformation));
        expectedResult.setPurchasedCommodities(List.of(commodity));
        final Commodity commodity1 = new Commodity();
        commodity1.setCommodityId("commodityId");
        final CommodityRanking commodityRanking1 = new CommodityRanking();
        commodityRanking1.setCommodityRank(new BigInteger("100"));
        commodityRanking1.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking1.setCommodityViewPageCnt(new BigInteger("100"));
//        
        commodity1.setCommodityRanking(commodityRanking1);
        final UserActionTracing userActionTracing1 = new UserActionTracing();
        userActionTracing1.setEventId(0L);
        userActionTracing1.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing1.setTracingType(0);
        commodity1.setUserActionTracingList(List.of(userActionTracing1));
        final CourseInformation courseInformation1 = new CourseInformation();
        commodity1.setCourseList(List.of(courseInformation1));
        expectedResult.setPublishedCommodities(List.of(commodity1));
        expectedResult.setUserUsername("userUsername");

        // Configure UserRepository.findUserByKeycloakId(...).
        final User user = new User();
        user.setUserId("userId");
        final Commodity commodity2 = new Commodity();
        commodity2.setCommodityId("commodityId");
        final CommodityRanking commodityRanking2 = new CommodityRanking();
        commodityRanking2.setCommodityRank(new BigInteger("100"));
        commodityRanking2.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking2.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking2.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity2.setCommodityRanking(commodityRanking2);
        final UserActionTracing userActionTracing2 = new UserActionTracing();
        userActionTracing2.setEventId(0L);
        userActionTracing2.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing2.setTracingType(0);
        commodity2.setUserActionTracingList(List.of(userActionTracing2));
        final CourseInformation courseInformation2 = new CourseInformation();
        commodity2.setCourseList(List.of(courseInformation2));
        user.setPurchasedCommodities(List.of(commodity2));
        final Commodity commodity3 = new Commodity();
        commodity3.setCommodityId("commodityId");
        final CommodityRanking commodityRanking3 = new CommodityRanking();
        commodityRanking3.setCommodityRank(new BigInteger("100"));
        commodityRanking3.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking3.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking3.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity3.setCommodityRanking(commodityRanking3);
        final UserActionTracing userActionTracing3 = new UserActionTracing();
        userActionTracing3.setEventId(0L);
        userActionTracing3.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing3.setTracingType(0);
        commodity3.setUserActionTracingList(List.of(userActionTracing3));
        final CourseInformation courseInformation3 = new CourseInformation();
        commodity3.setCourseList(List.of(courseInformation3));
        user.setPublishedCommodities(List.of(commodity3));
        user.setUserUsername("userUsername");
        when(mockUserRepository.findUserByKeycloakId("kcId")).thenReturn(user);

        // Run the test
        final User result = userServiceImplUnderTest.getUserInfoByKcId("kcId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetUserInfoByKcId_UserRepositoryReturnsNull() {
        // Setup
        when(mockUserRepository.findUserByKeycloakId("kcId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.getUserInfoByKcId("kcId"))
                .isInstanceOf(UserNullException.class);
    }






    @Test
    public void testAddUserInfo_CartServiceImplThrowsException() throws Exception {
        // Setup
        final AddUserInfoRequest requestBody = new AddUserInfoRequest();
        final Tag tag = new Tag();
        tag.setTagId("tagId");
        tag.setTagName("tagName");
        tag.setTagInfo("tagInfo");
        requestBody.setTagList(List.of(tag));
        requestBody.setUserUsername("userUsername");
        requestBody.setUserAge(0);
        requestBody.setUserFirstname("userFirstname");
        requestBody.setUserLastname("userLastname");
        requestBody.setUserEmailVerified(false);
        requestBody.setUserEnabled(false);
        requestBody.setUserEmail("userEmail");
        requestBody.setUserContact("userContact");
        requestBody.setUserIntroduction("userIntroduction");
        requestBody.setUserAvatar("userAvatar");
        requestBody.setKeycloakId("keycloakId");

        // Configure UserRepository.findUserByUserUsername(...).
        final User user = new User();
        user.setUserId("userId");
        final Commodity commodity = new Commodity();
        commodity.setCommodityId("commodityId");
        final CommodityRanking commodityRanking = new CommodityRanking();
        commodityRanking.setCommodityRank(new BigInteger("100"));
        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity.setCommodityRanking(commodityRanking);
        final UserActionTracing userActionTracing = new UserActionTracing();
        userActionTracing.setEventId(0L);
        userActionTracing.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing.setTracingType(0);
        commodity.setUserActionTracingList(List.of(userActionTracing));
        final CourseInformation courseInformation = new CourseInformation();
        commodity.setCourseList(List.of(courseInformation));
        user.setPurchasedCommodities(List.of(commodity));
        final Commodity commodity1 = new Commodity();
        commodity1.setCommodityId("commodityId");
        final CommodityRanking commodityRanking1 = new CommodityRanking();
        commodityRanking1.setCommodityRank(new BigInteger("100"));
        commodityRanking1.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking1.setCommodityViewPageCnt(new BigInteger("100"));
//        
        commodity1.setCommodityRanking(commodityRanking1);
        final UserActionTracing userActionTracing1 = new UserActionTracing();
        userActionTracing1.setEventId(0L);
        userActionTracing1.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing1.setTracingType(0);
        commodity1.setUserActionTracingList(List.of(userActionTracing1));
        final CourseInformation courseInformation1 = new CourseInformation();
        commodity1.setCourseList(List.of(courseInformation1));
        user.setPublishedCommodities(List.of(commodity1));
        user.setUserUsername("userUsername");
        when(mockUserRepository.findUserByUserUsername("userUsername")).thenReturn(user);

        // Configure UserRepository.save(...).
        final User user1 = new User();
        user1.setUserId("userId");
        final Commodity commodity2 = new Commodity();
        commodity2.setCommodityId("commodityId");
        final CommodityRanking commodityRanking2 = new CommodityRanking();
        commodityRanking2.setCommodityRank(new BigInteger("100"));
        commodityRanking2.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking2.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking2.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity2.setCommodityRanking(commodityRanking2);
        final UserActionTracing userActionTracing2 = new UserActionTracing();
        userActionTracing2.setEventId(0L);
        userActionTracing2.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing2.setTracingType(0);
        commodity2.setUserActionTracingList(List.of(userActionTracing2));
        final CourseInformation courseInformation2 = new CourseInformation();
        commodity2.setCourseList(List.of(courseInformation2));
        user1.setPurchasedCommodities(List.of(commodity2));
        final Commodity commodity3 = new Commodity();
        commodity3.setCommodityId("commodityId");
        final CommodityRanking commodityRanking3 = new CommodityRanking();
        commodityRanking3.setCommodityRank(new BigInteger("100"));
        commodityRanking3.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking3.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking3.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity3.setCommodityRanking(commodityRanking3);
        final UserActionTracing userActionTracing3 = new UserActionTracing();
        userActionTracing3.setEventId(0L);
        userActionTracing3.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing3.setTracingType(0);
        commodity3.setUserActionTracingList(List.of(userActionTracing3));
        final CourseInformation courseInformation3 = new CourseInformation();
        commodity3.setCourseList(List.of(courseInformation3));
        user1.setPublishedCommodities(List.of(commodity3));
        user1.setUserUsername("userUsername");
//        when(mockUserRepository.save(new User())).thenReturn(user1);
//        when(mockCartService.addCart("userId")).thenThrow(Exception.class);

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.addUserInfo(requestBody)).isInstanceOf(Exception.class);
    }



    @Test
    public void testUpdateUserInfo_UserRepositoryFindUserByUserIdReturnsNull() {
        // Setup
        final User user = new User();
        user.setUserId("userId");
        final Commodity commodity = new Commodity();
        commodity.setCommodityId("commodityId");
        final CommodityRanking commodityRanking = new CommodityRanking();
        commodityRanking.setCommodityRank(new BigInteger("100"));
        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity.setCommodityRanking(commodityRanking);
        final UserActionTracing userActionTracing = new UserActionTracing();
        userActionTracing.setEventId(0L);
        userActionTracing.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing.setTracingType(0);
        commodity.setUserActionTracingList(List.of(userActionTracing));
        final CourseInformation courseInformation = new CourseInformation();
        commodity.setCourseList(List.of(courseInformation));
        user.setPurchasedCommodities(List.of(commodity));
        final Commodity commodity1 = new Commodity();
        commodity1.setCommodityId("commodityId");
        final CommodityRanking commodityRanking1 = new CommodityRanking();
        commodityRanking1.setCommodityRank(new BigInteger("100"));
        commodityRanking1.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking1.setCommodityViewPageCnt(new BigInteger("100"));
        
        commodity1.setCommodityRanking(commodityRanking1);
        final UserActionTracing userActionTracing1 = new UserActionTracing();
        userActionTracing1.setEventId(0L);
        userActionTracing1.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing1.setTracingType(0);
        commodity1.setUserActionTracingList(List.of(userActionTracing1));
        final CourseInformation courseInformation1 = new CourseInformation();
        commodity1.setCourseList(List.of(courseInformation1));
        user.setPublishedCommodities(List.of(commodity1));
        user.setUserUsername("userUsername");

        when(mockUserRepository.findUserByUserId("userId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.updateUserInfo(user)).isInstanceOf(Exception.class);
    }



    @Test
    public void testDeleteUser() throws Exception {
        // Setup
        // Configure UserRepository.findUserByUserId(...).
        final User user = new User();
        user.setUserId("userId");
        final Commodity commodity = new Commodity();
        commodity.setCommodityId("commodityId");
        final CommodityRanking commodityRanking = new CommodityRanking();
        commodityRanking.setCommodityRank(new BigInteger("100"));
        commodityRanking.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking.setCommodityViewPageCnt(new BigInteger("100"));
//        commodityRanking.setCommodityExposureUserCnt(new BigInteger("100"));
        commodity.setCommodityRanking(commodityRanking);
        final UserActionTracing userActionTracing = new UserActionTracing();
        userActionTracing.setEventId(0L);
        userActionTracing.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing.setTracingType(0);
        commodity.setUserActionTracingList(List.of(userActionTracing));
        final CourseInformation courseInformation = new CourseInformation();
        commodity.setCourseList(List.of(courseInformation));
        user.setPurchasedCommodities(List.of(commodity));
        final Commodity commodity1 = new Commodity();
        commodity1.setCommodityId("commodityId");
        final CommodityRanking commodityRanking1 = new CommodityRanking();
        commodityRanking1.setCommodityRank(new BigInteger("100"));
        commodityRanking1.setCommodityViewUserCnt(new BigInteger("100"));
        commodityRanking1.setCommodityViewPageCnt(new BigInteger("100"));
        
        commodity1.setCommodityRanking(commodityRanking1);
        final UserActionTracing userActionTracing1 = new UserActionTracing();
        userActionTracing1.setEventId(0L);
        userActionTracing1.setTracingTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userActionTracing1.setTracingType(0);
        commodity1.setUserActionTracingList(List.of(userActionTracing1));
        final CourseInformation courseInformation1 = new CourseInformation();
        commodity1.setCourseList(List.of(courseInformation1));
        user.setPublishedCommodities(List.of(commodity1));
        user.setUserUsername("userUsername");
        when(mockUserRepository.findUserByUserId("userId")).thenReturn(user);

        // Run the test
        final String result = userServiceImplUnderTest.deleteUser("userId");

        // Verify the results
        assertThat(result).isEqualTo("Delete successfully");
        verify(mockUserRepository).deleteById("userId");
    }

    @Test
    public void testDeleteUser_UserRepositoryFindUserByUserIdReturnsNull() {
        // Setup
        when(mockUserRepository.findUserByUserId("userId")).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.deleteUser("userId")).isInstanceOf(Exception.class);
    }






}
