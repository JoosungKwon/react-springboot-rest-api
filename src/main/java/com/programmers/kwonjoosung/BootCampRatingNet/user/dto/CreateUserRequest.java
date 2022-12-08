package com.programmers.kwonjoosung.BootCampRatingNet.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserRequest {
    private String nickName;
    private String password;
    private String email;
    private String phone;
    private String bootCamp;
}
