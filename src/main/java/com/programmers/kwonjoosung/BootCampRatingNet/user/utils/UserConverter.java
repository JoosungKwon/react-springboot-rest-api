package com.programmers.kwonjoosung.BootCampRatingNet.user.utils;

import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.CreateUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.UserResponseDto;
import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.User;

public class UserConverter {

    private UserConverter() {
    }

    public static User toUser(CreateUserRequest request) {
        return User.builder()
                .nickName(request.getNickName())
                .password(request.getPassword())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .bootCamp(request.getBootCamp())
                .build();
    }

    public static UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId().toString())
                .nickName(user.getNickName())
                .email(user.getEmail().getAddress())
                .phone(user.getPhone())
                .address(user.getAddress())
                .bootCamp(user.getBootCamp())
                .build();
    }
}