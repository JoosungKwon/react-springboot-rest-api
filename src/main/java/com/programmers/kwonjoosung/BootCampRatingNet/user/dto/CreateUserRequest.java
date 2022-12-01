package com.programmers.kwonjoosung.BootCampRatingNet.user.dto;

import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.Email;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserRequest {
    private final String nickName;
    private final String password;
    private final Email email;
    private final String phone;
    private String address;
    private String bootCamp;
}
