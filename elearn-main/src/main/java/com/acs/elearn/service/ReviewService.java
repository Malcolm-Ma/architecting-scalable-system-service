package com.acs.elearn.service;

import com.acs.elearn.dao.model.Review;
import com.acs.elearn.vo.AddReviewRequest;

import java.util.List;

public interface ReviewService {
    Review addReview(AddReviewRequest requestBody) throws Exception;

    String deleteReview(String ReviewId) throws Exception;

    String updateReview(Review review) throws Exception;

    List<Review> displayReviewList(String commodityId) throws Exception;

}
