package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.Review;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.ReviewRepository;
import com.acs.elearn.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    final CommodityRepository commodityRepository;
    final ReviewRepository reviewRepository;

    public ReviewServiceImpl(CommodityRepository commodityRepository, ReviewRepository reviewRepository) {
        this.commodityRepository = commodityRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(Review review) throws Exception {
        return null;
    }

    @Override
    public String deleteReview(String ReviewId) throws Exception {
        return null;
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
