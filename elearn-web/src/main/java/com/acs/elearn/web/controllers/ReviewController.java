package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.Review;
import com.acs.elearn.service.impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {
    final ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping(path = "/display")
    @ResponseBody
    ResponseEntity<ResponseModel<List<Review>>> displayReviewList(@NotNull @RequestParam String commodityId) {
        try {
            List<Review> res = reviewService.displayReviewList(commodityId);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

//    @PostMapping(path = "/create")
//    @ResponseBody
//        ResponseEntity<ResponseModel<List<Review>>> displayReviewList(@NotNull @RequestBody AddReviewRequest requestBody) {
//            try {
//                List<Review> res = reviewService.displayReviewList(commodityId);
//                return ResponseHandler.generateResponse("success",HttpStatus.OK,res);
//            } catch(Exception e) {
//                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
//            }
//    ResponseEntity<ResponseModel<User>> getUserInfo(@NotNull @RequestParam String userId) {
//        try {
//            User res = userService.getUserInfo(userId);
//            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
//        }
//    }
}
