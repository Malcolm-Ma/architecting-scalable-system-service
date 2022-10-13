package com.acs.elearn.service;

import com.acs.elearn.dao.model.ShoppingCart;

public interface CartService {
    ShoppingCart displayCart(String userId);

    String addCommodityToCart(String commodityId, String userId) throws Exception;

    String deleteCommodityFromCart(String commodityId, String cartId) throws Exception;


}
