package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class BootCamp {
    private final UUID campId;
    private final String name;
    private String location;
    private String description;
//    private long rating;
//    private List<Review> reviews; -> 어떻게 추후에 setting 하게 할것인가?
}
