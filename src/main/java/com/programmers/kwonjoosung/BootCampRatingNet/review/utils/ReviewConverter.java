package com.programmers.kwonjoosung.BootCampRatingNet.review.utils;

import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.CreateReviewRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.ReviewResponseDto;
import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;

import java.util.UUID;

public class ReviewConverter {

    private ReviewConverter() {
    }

    public static ReviewResponseDto toResponse(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getReviewId().toString())
                .campName(review.getCampName())
                .userNickName(review.getUserNickName())
                .title(review.getTitle())
                .comment(review.getComment())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(CreateReviewRequest request) {
        return Review.builder()
                .reviewId(UUID.randomUUID())
                .campName(request.getCampName())
                .userNickName(request.getUserNickName())
                .title(request.getTitle())
                .comment(request.getComment())
                .rating(request.getRating())
                .build();
    }
}