package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;

import java.util.List;

public interface CartService {
    List<Commodity> displayCart(String cartId);

    List<Commodity> addCommodityToCart(String commodityId, String cartId);


}
