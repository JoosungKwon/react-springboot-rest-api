package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class BootCamp {
    private final UUID campId;
    private final String name;
    private String location;
    private String description;
    private List<Review> reviews;
}
