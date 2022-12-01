package com.programmers.kwonjoosung.BootCampRatingNet.review.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class Review {
    private final UUID reviewId;
    private final UUID campId;
    private final UUID userId;
    private final String title;
    private final String comment;
    private final long rating;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
