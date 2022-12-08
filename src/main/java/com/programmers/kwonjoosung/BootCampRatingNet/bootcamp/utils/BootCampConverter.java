package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.utils;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampInfoResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampWithReviewResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.CreateBootCampRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.entity.BootCamp;
import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;

import java.util.UUID;

public class BootCampConverter {

    private BootCampConverter() {
    }

    public static BootCampInfoResponse toInfoResponse(BootCamp bootCamp) {
        return toInfoResponse(bootCamp,0);
    }

    public static BootCampInfoResponse toInfoResponse(BootCamp bootCamp, double rating) {
        return BootCampInfoResponse.builder()
                .campId(bootCamp.getCampId().toString())
                .name(bootCamp.getName())
                .description(bootCamp.getDescription())
                .image(bootCamp.getImage())
                .rating(rating)
                .build();
    }

    public static BootCamp toBootCamp(CreateBootCampRequest request) {
        return BootCamp.builder()
                .campId(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .build();
    }

    public static BootCampWithReviewResponse toResponseWithReview(BootCamp bootCamp) {
        return BootCampWithReviewResponse.builder()
                .campId(bootCamp.getCampId().toString())
                .name(bootCamp.getName())
                .description(bootCamp.getDescription())
                .reviews(bootCamp.getReviews())
                .rating(bootCamp.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0))
                .build();
    }
}