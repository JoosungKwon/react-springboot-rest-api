package com.programmers.kwonjoosung.BootCampRatingNet.user.service;

import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.CreateUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.DeleteUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse findByUserId(UUID userId);

    UserResponse findByNickName(String nickName);

    void deleteUser(DeleteUserRequest request);

}
