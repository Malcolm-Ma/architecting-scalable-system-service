package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.Role;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.RoleRepository;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getUserInfo(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    // add user into database
    @Override
    public String addUserInfo(User user) throws Exception {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        Role curRole;
        if(user.getUserRole() != null) {
            curRole = roleRepository.findRoleByRoleId(user.getUserRole().getRoleId());
        } else {
            curRole = roleRepository.findRoleByRoleId(Long.valueOf("1"));
        }
        if (curUser == null) {
            user.setUserRole(curRole);
            userRepository.save(user);
            return "Add successfully";
        } else {
            throw new Exception("Add failed, user already existed.");
        }
    }

    // update user's information
    @Override
    public String updateUserInfo(User user) throws Exception {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        if (curUser == null) {
            throw new Exception("User is not exist.");
        }
        BeanUtil.copyProperties(user, curUser, CopyOptions.create().setIgnoreNullValue(true));
        userRepository.save(curUser);
        return "Add successfully";
    }

    @Override
    public String deleteUser( String userId) throws Exception {
        User curUser = userRepository.findUserByUserId(userId);
        if (curUser == null) {
            throw new Exception("User is not existed");
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
        User curMerchant =userRepository.findUserByUserId(userId);
        return curMerchant.getPublishedCommodities();
    }

}
