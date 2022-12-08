package com.programmers.kwonjoosung.BootCampRatingNet.review.utils;

import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.CreateReviewRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.ReviewResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewConverter {

    private ReviewConverter() {
    }

    public static ReviewResponse toResponse(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getReviewId().toString())
//                .campName(review.getCampName())
//                .userNickName(review.getUserNickName())
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(CreateReviewRequest request,UUID userId) {
        return Review.builder()
                .reviewId(UUID.randomUUID())
                .userId(userId)
                .campId(request.getCampId())
                .title(request.getTitle())
                .content(request.getContent())
                .rating(request.getRating())
                .createdAt(LocalDateTime.now())
                .build();
    }


}