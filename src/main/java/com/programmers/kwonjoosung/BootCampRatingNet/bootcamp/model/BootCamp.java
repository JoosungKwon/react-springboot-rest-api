package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class BootCamp {
    private final UUID campId;
    private final String campName;
    private String campLocation;
    private String campDescription;
    private List<Review> reviews;
}
