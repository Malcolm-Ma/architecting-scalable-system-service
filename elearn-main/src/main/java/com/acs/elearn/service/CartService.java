package com.acs.elearn.service;

import com.acs.elearn.dao.model.ShoppingCart;
import com.acs.elearn.dao.model.User;

public interface CartService {
    ShoppingCart displayCart(String userId);

    ShoppingCart addCart(String userId) throws Exception;

    String addCommodityToCart(String commodityId, String userId) throws Exception;

    String deleteCommodityFromCart(String commodityId, String userId) throws Exception;


}
