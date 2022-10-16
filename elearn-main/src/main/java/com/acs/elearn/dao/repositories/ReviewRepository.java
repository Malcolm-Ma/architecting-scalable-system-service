package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findReviewsByCommodity(Commodity commodity);
    void deleteById(String reviewId);

    Review findReviewByReviewId(String reviewId);
}
