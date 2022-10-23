package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.ShoppingCart;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.ShoppingCartRepository;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    final ShoppingCartRepository shoppingCartRepository;
    final UserRepository userRepository;
    final CommodityRepository commodityRepository;

    @Autowired
    public CartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, CommodityRepository commodityRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.commodityRepository = commodityRepository;
    }

    @Override
    public ShoppingCart displayCart(String userId) {
        User newUser = userRepository.findUserByUserId(userId);
        ShoppingCart newCart = newUser.getUserShoppingCart();
        return newCart;
    }

    @Override
    public ShoppingCart addCart(String userId) throws Exception {
        try {
            User user = userRepository.findUserByUserId(userId);
            if (shoppingCartRepository.findShoppingCartByUser(user) != null) {
                throw new Exception("cart already exist.");
            }
            ShoppingCart newCart = new ShoppingCart();
            newCart.setUser(user);
            shoppingCartRepository.save(newCart);
            return newCart;
        } catch(Exception e) {throw e;}
    }


    @Override
    public String addCommodityToCart(String commodityId, String userId) throws Exception {
        User newUser = userRepository.findUserByUserId(userId);
        ShoppingCart newCart = newUser.getUserShoppingCart();
        Commodity chosenCommodity = commodityRepository.findByCommodityId(commodityId);
        List<Commodity> newCommodity = (newCart.getCartCommodity());
        if (newCommodity.contains(chosenCommodity)) {
            throw new Exception("Commodity is already in Cart");
        }
        newCommodity.add(chosenCommodity);
        shoppingCartRepository.save(newCart);
        return "successfully added Commodity";
    }

    @Override
    public String deleteCommodityFromCart(String commodityId, String cartId) throws Exception {
        ShoppingCart newCart = shoppingCartRepository.findShoppingCartByCartId(cartId);
        Commodity chosenCommodity = commodityRepository.findByCommodityId(commodityId);
        List<Commodity> newCommodity = newCart.getCartCommodity();
        if (newCommodity.contains(chosenCommodity)) {
            newCommodity.remove(chosenCommodity);
            newCart.setCartCommodity(newCommodity);
        } else {
            throw new Exception("commodity is not in cart.");
        }
        shoppingCartRepository.save(newCart);
        return "successfully delete commodity";
    }
}
