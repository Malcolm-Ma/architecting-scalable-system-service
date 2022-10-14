package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.ShoppingCart;
import com.acs.elearn.service.impl.CartServiceImpl;
import com.sun.istack.NotNull;
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
    ResponseEntity<ResponseModel<ShoppingCart>> displayCart(@NotNull @RequestParam String userId) {
        try {
            ShoppingCart res = cartService.displayCart(userId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/add")
    @ResponseBody
    ResponseEntity<ResponseModel<ShoppingCart>> addCart(@NotNull @RequestParam String userId) {
        try {
            ShoppingCart res = cartService.addCart(userId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/add_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> addCommodityToCart(@NotNull @RequestBody String commodityId, String userId) {
        try {
            String res = cartService.addCommodityToCart(commodityId, userId);
            return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/delete_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> deleteCommodityFromCart(@NotNull String commodityId, String userId) {
        try {
            String res = cartService.deleteCommodityFromCart(commodityId, userId);
            return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
