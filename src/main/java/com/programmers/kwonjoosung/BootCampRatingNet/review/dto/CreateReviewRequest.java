package com.programmers.kwonjoosung.BootCampRatingNet.review.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateReviewRequest {
    private final String campName;
    private final String userNickName;
    private final String title;
    private final String comment;
    private final long rating;
}
