package com.programmers.kwonjoosung.BootCampRatingNet.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReviewResponse {
    private String reviewId;
//    private String campName;
//    private String userNickName;
    private String title;
    private String content;
    private long rating;
    private LocalDateTime createdAt;
}
