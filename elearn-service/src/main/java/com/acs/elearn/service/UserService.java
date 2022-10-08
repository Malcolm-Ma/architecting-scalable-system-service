package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;

import java.util.List;

public interface UserService {
    public User getUserInfo(String userId);

    public List<Commodity> getUserPurchasedCommodity(String userId);

    public List<Commodity> getMerchantCommodity(String userId);

    public User editUserInfo(User user);
}
