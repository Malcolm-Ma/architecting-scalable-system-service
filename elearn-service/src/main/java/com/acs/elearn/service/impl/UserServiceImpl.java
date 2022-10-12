package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserInfo(@NotNull String userId) {
        return userRepository.findUserByUserId(userId);
    }

    // add user into database
    @Override
    public String addUserInfo(@NotNull User user) throws Exception {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        if (curUser == null) {
            userRepository.save(user);
            return "Add successfully";
        } else {
            throw new Exception("Add failed, user already existed.");
        }
    }

    // update user's information
    @Override
    public String updateUserInfo(@NotNull User user) throws Exception {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        if (curUser == null) {
            throw new Exception("User is not exist.");
        }
        BeanUtil.copyProperties(user, curUser, CopyOptions.create().setIgnoreNullValue(true));
        userRepository.save(curUser);
        return "Add successfully";
    }

    @Override
    public String deleteUser(@NotNull String userId) throws Exception {
        User curUser = userRepository.findUserByUserId(userId);
        if (curUser == null) {
            throw new Exception("User is not existed");
        } else {
            userRepository.deleteById(userId);
            return "Delete successfully";
        }
    }

    // TODO Change Buyer and Merchant to User
//    @Override
//    public List<Commodity> getUserPurchasedCommodity(@NotNull String userId) {
//        Buyer curBuyer = buyerRepository.findBuyerByUserId(userId);
//        return curBuyer.getPurchasedCommodities();
//    }
//
//    @Override
//    public List<Commodity> getMerchantCommodity(@NotNull String userId) {
//        Merchant curMerchant = merchantRepository.getMerchantByUserId(userId);
//        return curMerchant.getPublishedCommodities();
//    }

}
