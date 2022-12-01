package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BootCampResponseDto {
    private final String name;
    private final String location;
    private final String description;
}
