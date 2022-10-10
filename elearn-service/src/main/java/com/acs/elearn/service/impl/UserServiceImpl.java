package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.UserService;
import org.apache.http.util.Asserts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import static org.apache.http.util.Asserts.*;

@Service
public class UserServiceImpl implements UserService {

    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserInfo(String userId) {

        return userRepository.findUserByUserId(userId);
    }

    @Override
    public String addUserInfo(User user) {
//        try {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        if (curUser == null) {
            BeanUtil.copyProperties(user, curUser, CopyOptions.create().setIgnoreNullValue(true));
            userRepository.save(curUser);
//        userRepository.save(user);
            return "Add successfully";
//        } catch(Exception e){
//            Asserts.fail(e.getMessage());
//            return null;
//        }
        }
        else {
        return "failed";
        }
    }

    @Override
    public String updateUserInfo(User user) {
//        try {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        if (curUser == null) {
            return null;
        }
        BeanUtil.copyProperties(user, curUser, CopyOptions.create().setIgnoreNullValue(true));
        userRepository.save(curUser);
//        userRepository.save(user);
        return "Add successfully";
//        } catch(Exception e){
//            Asserts.fail(e.getMessage());
//            return null;
//        }
    }

    @Override
    public List<Commodity> getUserPurchasedCommodity(String userId) {
        return null;
    }

    @Override
    public List<Commodity> getMerchantCommodity(String userId) {
        return null;
    }




}
