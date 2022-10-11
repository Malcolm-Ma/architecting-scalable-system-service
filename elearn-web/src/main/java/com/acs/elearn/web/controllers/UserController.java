package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
//         @ResponseBody means the returned String is the response, not a view name
//         @RequestParam means it is a parameter from the GET or POST request
    User getUserInfo(@RequestParam String userId) {
        // This returns a JSON or XML with the users

        return userService.getUserInfo(userId);
    }

    @PostMapping(path = "/update")
    @ResponseBody
    ResponseEntity<Object> updateUserInfo(@RequestBody User user) {
        String res = userService.updateUserInfo(user);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);

    }

    @PostMapping(path = "/add")
    @ResponseBody
    ResponseEntity<Object> addUserInfo(@RequestBody User user) {
        String res = userService.addUserInfo(user);
        return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
    }

    @PostMapping(path = "/delete")
    @ResponseBody
    ResponseEntity<Object> deleteUser(@RequestParam String userId) {
        String res = userService.deleteUser(userId);
        return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
    }

    @GetMapping(path = "/get_buyer_commodity")
    @ResponseBody
    ResponseEntity<Object> getUserPurchasedCommodity(@RequestParam String userId) {
        // This returns a JSON or XML with the users
        List<Commodity> res = userService.getUserPurchasedCommodity(userId);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @GetMapping(path = "/get_merchant_commodity")
    @ResponseBody
    ResponseEntity<Object> getMerchantCommodity(@RequestParam String userId) {
        List<Commodity> res = userService.getMerchantCommodity(userId);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }
    // e.message,
}
