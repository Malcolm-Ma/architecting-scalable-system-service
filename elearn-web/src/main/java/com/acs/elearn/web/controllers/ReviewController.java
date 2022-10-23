package com.acs.elearn.web.controllers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.Review;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.service.ReviewService;
import com.acs.elearn.vo.AddReviewRequest;
import com.acs.elearn.vo.UpdateReviewRequest;
import com.acs.elearn.vo.UpdateUserInfoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/api/review")
public class ReviewController {
    final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
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

    @PostMapping(path = "/update")
    @ResponseBody
    ResponseEntity<ResponseModel<Review>> updateReview(@NotNull @RequestBody UpdateReviewRequest requestBody) {
        try {
            Review res = reviewService.updateReview(requestBody);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
