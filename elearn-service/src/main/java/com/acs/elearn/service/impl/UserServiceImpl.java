package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.model.Buyer;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.Merchant;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.BuyerRepository;
import com.acs.elearn.dao.repositories.MerchantRepository;
import com.acs.elearn.dao.repositories.TransactionRepository;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.UserService;
import org.apache.http.util.Asserts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import static org.apache.http.util.Asserts.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    MerchantRepository merchantRepository;


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
            return "Add successfully";
//        } catch(ChangeSetPersister.NotFoundException){
//            Asserts.fail(e.getMessage());
//            return null;
//        }
        } else {
            return "failed";
        }
    }

    @Override
    public String updateUserInfo(User user) {
//        try {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        if (curUser == null) {
            return "User Not existed before, please check the information";
        }
        BeanUtil.copyProperties(user, curUser, CopyOptions.create().setIgnoreNullValue(true));
        userRepository.save(curUser);
        return "Add successfully";
//        } catch(Exception e){
//            Asserts.fail(e.getMessage());
//            return null;
//        }
    }

    @Override
    public String deleteUser(String userId) {
        User curUser = userRepository.findUserByUserId(userId);
        // try catch 去写
        if (curUser == null) {
            return "User Not existed before, please check the information";
        }
        else {
            userRepository.deleteById(userId);
            return "Delete successfully";
        }
    }

    @Override
    public List<Commodity> getUserPurchasedCommodity(String userId) {
        Buyer curBuyer = buyerRepository.findBuyerByUserId(userId);
        return curBuyer.getPurchasedCommodities();
    }

    @Override
    public List<Commodity> getMerchantCommodity(String userId) {
        Merchant curMerchant = merchantRepository.getMerchantByUserId(userId);
        return curMerchant.getPublishedCommodities();
    }


}
