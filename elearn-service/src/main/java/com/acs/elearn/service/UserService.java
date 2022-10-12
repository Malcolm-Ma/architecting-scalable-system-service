package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User getUserInfo(String userId);

    List<Commodity> getUserPurchasedCommodity(String userId);

    List<Commodity> getMerchantCommodity(String userId);
    String updateUserInfo(User user);

    String addUserInfo(User user) throws Exception;

    String deleteUser(String userId);
}
