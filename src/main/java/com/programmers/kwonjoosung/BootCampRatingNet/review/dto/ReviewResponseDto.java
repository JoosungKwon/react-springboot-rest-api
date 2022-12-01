package com.programmers.kwonjoosung.BootCampRatingNet.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReviewResponseDto {
    private final String reviewId;
    private final String campName;
    private final String userNickName;
    private final String title;
    private final String comment;
    private final long rating;
    private final LocalDateTime createdAt;
}
