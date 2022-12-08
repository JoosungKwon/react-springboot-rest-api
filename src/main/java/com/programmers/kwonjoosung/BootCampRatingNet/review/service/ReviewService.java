package com.programmers.kwonjoosung.BootCampRatingNet.review.service;

import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.CreateReviewRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewResponse createReview(CreateReviewRequest request);

    List<ReviewResponse> findAll();

    void deleteByReviewId(UUID reviewId);

}
