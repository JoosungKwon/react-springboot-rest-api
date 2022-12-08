package com.programmers.kwonjoosung.BootCampRatingNet.user.utils;

import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.CreateUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.UserResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.Email;
import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.User;

import java.util.UUID;

public class UserConverter {

    private UserConverter() {
    }

    public static User toUser(CreateUserRequest request) {
        return User.builder()
                .userId(UUID.randomUUID())
                .nickName(request.getNickName())
                .password(request.getPassword())
                .email(new Email(request.getEmail()))
                .phone(request.getPhone())
                .bootCamp(request.getBootCamp())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId().toString())
                .nickName(user.getNickName())
                .email(user.getEmail().getAddress())
                .phone(user.getPhone())
                .bootCamp(user.getBootCamp())
                .build();
    }
}