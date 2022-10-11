package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.ShoppingCart;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cart")
public class CartController {
    final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "/display")
    @ResponseBody
    ResponseEntity<ResponseModel<ShoppingCart>> displayCart(@RequestParam String userId) {
        ShoppingCart res =  cartService.displayCart(userId);
        return ResponseHandler.generateResponse("success",HttpStatus.OK, res);
    }

    @PostMapping(path = "/add_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> addCommodityToCart(String commodityId, String userId)  {
        String res = cartService.addCommodityToCart(commodityId, userId);
        return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
    }

    @PostMapping(path = "/delete_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> deleteCommodityFromCart(String commodityId, String userId)  {
        String res = cartService.deleteCommodityFromCart(commodityId, userId);
        return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
    }

}
