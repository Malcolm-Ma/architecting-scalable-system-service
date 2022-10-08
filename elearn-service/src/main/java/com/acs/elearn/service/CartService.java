package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;

import java.util.List;

public interface CartService {
    public List<Commodity> displayCart(String cartId);

    public List<Commodity> addCommodityToCart(String commodityId, String cartId);


}
