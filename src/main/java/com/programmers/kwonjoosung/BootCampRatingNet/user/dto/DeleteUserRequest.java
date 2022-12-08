package com.programmers.kwonjoosung.BootCampRatingNet.user.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteUserRequest {
    private UUID userId;
    private String password;
}
