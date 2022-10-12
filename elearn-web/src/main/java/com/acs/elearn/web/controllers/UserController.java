package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    final
    UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/get")
    @ResponseBody
    ResponseEntity<ResponseModel<User>> getUserInfo(@RequestParam String userId) {
        try {
            User res = userService.getUserInfo(userId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/update")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> updateUserInfo(@RequestBody User user) {
        try {
            String res = userService.updateUserInfo(user);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    @PostMapping(path = "/add")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> addUserInfo(@RequestBody User user) {
        try {
            String res = userService.addUserInfo(user);
            return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/delete")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> deleteUser(@RequestParam String userId) {
        try {
            String res = userService.deleteUser(userId);
            return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/get_buyer_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<List<Commodity>>> getUserPurchasedCommodity(@RequestParam String userId) {
        // This returns a JSON or XML with the users
        try {
            List<Commodity> res = userService.getUserPurchasedCommodity(userId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/get_merchant_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<List<Commodity>>> getMerchantCommodity(@RequestParam String userId) {
        try {
            List<Commodity> res = userService.getMerchantCommodity(userId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
