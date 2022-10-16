package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.Review;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.ReviewRepository;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.ReviewService;
import com.acs.elearn.vo.AddReviewRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    final CommodityRepository commodityRepository;
    final ReviewRepository reviewRepository;
    final UserRepository userRepository;

    public ReviewServiceImpl(CommodityRepository commodityRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.commodityRepository = commodityRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review addReview(AddReviewRequest requestBody) throws Exception {
        User curUser = userRepository.findUserByUserId(requestBody.getUserId());
        Commodity curCommodity = commodityRepository.findByCommodityId(requestBody.getCommodityId());
        if (curUser == null) {
            throw new Exception("this user doesn't exist.");
        }
        if (curCommodity == null) {
            throw new Exception("this user doesn't exist.");
        }
        Review curReview = new Review();
        curReview.setUser(curUser);
        curReview.setCommodity(curCommodity);
        curReview.setReviewComment(requestBody.getReviewComment());
        curReview.setReviewStar(requestBody.getReviewStar());
        return reviewRepository.save(curReview);
    }

    @Override
    public String deleteReview(String reviewId) throws Exception {
        Review curReview = reviewRepository.findReviewByReviewId(reviewId);
        if (curReview == null) {
            throw new Exception("User doesn't existed");
        }
        reviewRepository.deleteById(reviewId);
        return "Delete successfully";
    }

    @Override
    public String updateReview(Review review) throws Exception {
        return null;
    }

    @Override
    public List<Review> displayReviewList(String commodityId) throws Exception {
        Commodity curCommodity = commodityRepository.findByCommodityId(commodityId);
        if (curCommodity != null) {
            return reviewRepository.findReviewsByCommodity(curCommodity);
        } else {
            throw new Exception("commodity Id is not in the database.");
        }
    }
}
