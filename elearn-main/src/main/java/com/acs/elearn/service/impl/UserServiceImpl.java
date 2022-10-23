package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.common.exception.UserNullException;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.ShoppingCart;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.UserService;
import com.acs.elearn.vo.AddUserInfoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final CartServiceImpl cartService;

    public UserServiceImpl(CartServiceImpl cartService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.cartService = cartService;
    }

    @Override
    public User getUserInfo(String userId) throws UserNullException {
        User user = userRepository.findUserByUserId(userId);
        if(user == null) {
            throw new UserNullException("User doesn't exist.");
        }
        return user;
    }

    @Override
    public User getUserInfoByKcId(String kcId) throws UserNullException {
        User user = userRepository.findUserByKeycloakId(kcId);
        if(user == null) {
            throw new UserNullException("User doesn't exist.");
        }
        return user;
    }

    @Override
    public User addUserInfo(AddUserInfoRequest requestBody) throws Exception {
        User user = new User();
        BeanUtil.copyProperties(requestBody, user, CopyOptions.create().setIgnoreNullValue(true));
        User curUser = userRepository.findUserByUserUsername(user.getUserUsername());
        if (curUser != null) {
            throw new Exception("Add failed, username already existed.");
        }
        User res = userRepository.save(user);
        ShoppingCart test = cartService.addCart(res.getUserId());
        return user;
    }

    // update user's information
    @Override
    public User updateUserInfo(User user) throws Exception {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        if (curUser == null) {
            throw new Exception("User is not exist.");
        }
        BeanUtil.copyProperties(user, curUser, CopyOptions.create().setIgnoreNullValue(true));
        return userRepository.save(curUser);
    }

    @Override
    public String deleteUser(String userId) throws Exception {
        User curUser = userRepository.findUserByUserId(userId);
        if (curUser == null) {
            throw new Exception("User doesn't existed");
        } else {
            userRepository.deleteById(userId);
            return "Delete successfully";
        }
    }

    @Override
    public List<Commodity> getUserPurchasedCommodity(String userId) {
        User curBuyer = userRepository.findUserByUserId(userId);
        return curBuyer.getPurchasedCommodities();
    }

    @Override
    public List<Commodity> getMerchantCommodity(String userId) {
        User curMerchant = userRepository.findUserByUserId(userId);
        return curMerchant.getPublishedCommodities();
    }

}
