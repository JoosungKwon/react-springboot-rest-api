package com.programmers.kwonjoosung.BootCampRatingNet.user.service;

import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataNotFoundException;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.CreateUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.DeleteUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.UserResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.programmers.kwonjoosung.BootCampRatingNet.user.utils.UserConverter.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService { // 닉네임으로 조회할 경우에 대한 메소드가 필요할까? 그때는 뭘 반환하는게 좋을까?

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        return toResponse(userRepository.save(toUser(request)));
    }

    @Override
    public UserResponse findByUserId(UUID userId) {
        return toResponse(userRepository.findByUserId(userId)
                .orElseThrow(() -> new DataNotFoundException("user", "user_id", userId.toString())));
    }

    @Override
    public UserResponse findByNickName(String nickName) {
        return toResponse(userRepository.findByNickName(nickName)
                .orElseThrow(() -> new DataNotFoundException("user", "nick_name", nickName)));
    }

    @Override
    public void deleteUser(DeleteUserRequest request) {
        userRepository.deleteUser(request.getUserId(), request.getPassword());
    }
}

