package com.programmers.kwonjoosung.BootCampRatingNet.review.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository {
    Review save(Review review);

    List<Review> findByReviewId(UUID ReviewId);

    List<Review> findByCampId(UUID ReviewId);

    List<Review> findByUserId(UUID ReviewId);

    List<Review> findAll();

    void deleteByReviewId(UUID reviewID);

    Double findRatingByCampId(UUID campId);
}
