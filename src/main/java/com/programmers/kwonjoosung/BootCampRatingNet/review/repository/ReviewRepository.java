package com.programmers.kwonjoosung.BootCampRatingNet.review.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository {
    Review save(Review review);

    Optional<Review> findByNickName(String nickName);

    Optional<Review> findByCampName(String campName);

    List<Review> findAll();

    void deleteByReviewId(UUID reviewID);
}
