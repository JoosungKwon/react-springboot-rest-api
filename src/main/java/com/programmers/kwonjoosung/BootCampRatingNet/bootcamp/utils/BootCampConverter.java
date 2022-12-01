package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.utils;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampResponseDto;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.CreateBootCampRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.entity.BootCamp;

import java.util.UUID;

public class BootCampConverter {

    private BootCampConverter() {
    }

    public static BootCampResponseDto toResponse(BootCamp bootCamp) {
        return new BootCampResponseDto(
                bootCamp.getName(),
                bootCamp.getLocation(),
                bootCamp.getDescription()
        );
    }

    public static BootCamp toBootCamp(CreateBootCampRequest request) {
        return BootCamp.builder()
                .campId(UUID.randomUUID())
                .name(request.getName())
                .location(request.getLocation())
                .description(request.getDescription())
                .build();
    }
}