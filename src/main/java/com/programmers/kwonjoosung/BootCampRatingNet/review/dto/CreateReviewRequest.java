package com.programmers.kwonjoosung.BootCampRatingNet.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CreateReviewRequest {
    private UUID campId;
    private String userNickName;
    private String userPassword;
    private String title;
    private String content;
    private long rating;
}
