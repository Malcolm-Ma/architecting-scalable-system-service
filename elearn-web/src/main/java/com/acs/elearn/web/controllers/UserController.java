package com.acs.elearn.web.controllers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.impl.UserServiceImpl;
import com.acs.elearn.vo.AddUserInfoRequest;
import com.acs.elearn.vo.UpdateUserInfoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    final
    UserServiceImpl userService;
    final UserRepository userRepository;

    public UserController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping(path = "/get")
    @ResponseBody
    ResponseEntity<ResponseModel<User>> getUserInfo(@NotNull @RequestParam String userId) {
        try {
            User res = userService.getUserInfo(userId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/update")
    @ResponseBody
    ResponseEntity<ResponseModel<User>> updateUserInfo(@NotNull @RequestBody UpdateUserInfoRequest requestBody) {
        try {
            User user = new User();
            BeanUtil.copyProperties(requestBody, user, CopyOptions.create().setIgnoreNullValue(true));
            User res = userService.updateUserInfo(user);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/add")
    @ResponseBody
    ResponseEntity<ResponseModel<User>> addUserInfo(@NotNull @RequestBody AddUserInfoRequest requestBody) {
        try {
            User user = new User();
            BeanUtil.copyProperties(requestBody, user, CopyOptions.create().setIgnoreNullValue(true));
            User res = userService.addUserInfo(user);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(path = "/delete")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> deleteUser(@NotNull @RequestParam String userId) {
        try {
            String res = userService.deleteUser(userId);
            return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/get_buyer_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<List<Commodity>>> getUserPurchasedCommodity(@NotNull @RequestParam String userId) {
        try {
            List<Commodity> res = userService.getUserPurchasedCommodity(userId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(path = "/get_merchant_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<List<Commodity>>> getMerchantCommodity(@NotNull @RequestParam String userId) {
        try {
            List<Commodity> res = userService.getMerchantCommodity(userId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
