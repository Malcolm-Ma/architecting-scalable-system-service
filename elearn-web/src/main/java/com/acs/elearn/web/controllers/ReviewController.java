package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.Review;
import com.acs.elearn.service.impl.ReviewServiceImpl;
import com.acs.elearn.vo.AddReviewRequest;
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

    @PostMapping(path = "/create")
    @ResponseBody
        ResponseEntity<ResponseModel<Review>> addReview(@NotNull @RequestBody AddReviewRequest requestBody) {
            try {
                Review res = reviewService.addReview(requestBody);
                return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
            } catch (Exception e) {
                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
    }

    @PostMapping(path = "/delete")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> deleteReview(@NotNull @RequestParam String reviewId) {
        try {
            String res = reviewService.deleteReview(reviewId);
            return ResponseHandler.generateResponse(res, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

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
