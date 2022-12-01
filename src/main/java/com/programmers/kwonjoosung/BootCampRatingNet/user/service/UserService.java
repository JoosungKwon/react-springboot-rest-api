package com.programmers.kwonjoosung.BootCampRatingNet.user.service;

import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.CreateUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.UserResponseDto;

import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(CreateUserRequest request);

    UserResponseDto findUserByUserId(UUID userId);

    UserResponseDto findByNickName(String nickName);

    void deleteUser(UUID userId, String password);

}
