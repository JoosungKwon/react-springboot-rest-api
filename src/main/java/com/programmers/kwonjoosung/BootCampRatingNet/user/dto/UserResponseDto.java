package com.programmers.kwonjoosung.BootCampRatingNet.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDto {
    private final String userId;
    private final String nickName;
    private final String email;
    private final String phone;
    private String address;
    private String bootCamp;
}
