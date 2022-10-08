package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User getUserInfo(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public List<Commodity> getUserPurchasedCommodity(String userId) {
        return null;
    }

    @Override
    public List<Commodity> getMerchantCommodity(String userId) {
        return null;
    }

    @Override
    public User editUserInfo(User user) {
        return null;
    }
}
