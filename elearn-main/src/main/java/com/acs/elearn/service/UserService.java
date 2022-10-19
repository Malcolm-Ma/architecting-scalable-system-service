package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.vo.AddUserInfoRequest;

import java.util.List;


public interface UserService {

    User getUserInfo(String userId) throws Exception;
    User getUserInfoByKcId(String kcId) throws Exception;
    List<Commodity> getUserPurchasedCommodity(String userId);

    List<Commodity> getMerchantCommodity(String userId);

    User updateUserInfo(User user) throws Exception;

    User addUserInfo(AddUserInfoRequest requestBody) throws Exception;

    String deleteUser(String userId) throws Exception;
}
