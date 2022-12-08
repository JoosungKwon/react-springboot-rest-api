package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BootCampInfoResponse {
    private final String campId;
    private final String name;
    private final String description;
    private final String image;
    private double rating;
}
