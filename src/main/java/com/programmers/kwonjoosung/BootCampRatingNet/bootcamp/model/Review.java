package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Review {
    private final UUID reviewId;
    private final UUID campId;
    private final UUID userId;
}
