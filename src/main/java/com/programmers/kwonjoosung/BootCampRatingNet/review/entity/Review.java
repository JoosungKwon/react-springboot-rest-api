package com.programmers.kwonjoosung.BootCampRatingNet.review.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class Review {
    private final UUID reviewId;
    private final String campName;
    private final String userNickName;
    private final String title;
    private final String comment;
    private final long rating;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
