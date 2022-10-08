package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.repositories.ShoppingCartRepository;
import com.acs.elearn.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Override
    public List<Commodity> displayCart(String cartId) {
        return null;
    }

    @Override
    public List<Commodity> addCommodityToCart(String commodityId, String cartId) {
        return null;
    }
}
