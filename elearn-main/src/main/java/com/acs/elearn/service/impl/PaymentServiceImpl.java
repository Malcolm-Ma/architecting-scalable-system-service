package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.ShoppingCart;
import com.acs.elearn.dao.model.Transaction;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.ShoppingCartRepository;
import com.acs.elearn.dao.repositories.TransactionRepository;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    final TransactionRepository transactionRepository;
    final CommodityRepository commodityRepository;
    final UserRepository userRepository;

    final ShoppingCartRepository shoppingCartRepository;

    public PaymentServiceImpl(TransactionRepository transactionRepository, CommodityRepository commodityRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.transactionRepository = transactionRepository;
        this.commodityRepository = commodityRepository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public Transaction createTransaction(String cartId) {
        ShoppingCart curCart = shoppingCartRepository.findShoppingCartByCartId(cartId);
        Transaction curTransaction = new Transaction();
        curTransaction.setUser(curCart.getUser());
        curTransaction.setCommodityList(curCart.getCartCommodity());
        double commodityRealPrice = 0;
        for (Commodity curCommodity: curCart.getCartCommodity()) {
            double curPrice = curCommodity.getCommodityPrice();
            double curDiscount = curCommodity.getCommodityDiscount();
            commodityRealPrice += curPrice*curDiscount;
        }
        curCart.setCartCommodity(null);
        shoppingCartRepository.save(curCart);
        curTransaction.setCommodityRealPrice(commodityRealPrice);
        transactionRepository.save(curTransaction);
        return curTransaction;
    }


}
