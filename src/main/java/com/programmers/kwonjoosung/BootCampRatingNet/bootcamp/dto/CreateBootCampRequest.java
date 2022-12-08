package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBootCampRequest {
    private String name;
    private String location;
    private String description;
    private String image;
}
