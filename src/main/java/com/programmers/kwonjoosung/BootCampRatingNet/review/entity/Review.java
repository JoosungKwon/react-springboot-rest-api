package com.programmers.kwonjoosung.BootCampRatingNet.review.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class Review {
    private UUID reviewId;
    private UUID userId;
    private UUID campId;
    private String title;
    private String content;
    private long rating;
    private LocalDateTime createdAt;
}
