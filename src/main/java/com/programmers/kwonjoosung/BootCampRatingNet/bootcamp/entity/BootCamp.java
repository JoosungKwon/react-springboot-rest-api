package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.entity;

import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class BootCamp {
    private final UUID campId;
    private final String name;
    private String description;
    private String image;
    private List<Review> reviews;

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
