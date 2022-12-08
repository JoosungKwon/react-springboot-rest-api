package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto;

import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class BootCampWithReviewResponse {
    private final String campId;
    private final String name;
    private final String description;
    private final double rating;
    private final List<Review> reviews;
}
