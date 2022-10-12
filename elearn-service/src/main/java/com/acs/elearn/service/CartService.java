package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.ShoppingCart;

import java.util.List;

public interface CartService {
    ShoppingCart displayCart(String userId);

    String addCommodityToCart(String commodityId, String userId) throws Exception;

    String deleteCommodityFromCart(String commodityId, String cartId) throws Exception;


}
