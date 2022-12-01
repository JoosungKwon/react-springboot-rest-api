package com.programmers.kwonjoosung.BootCampRatingNet.user.service;

import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataNotFoundException;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.CreateUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.UserResponseDto;
import com.programmers.kwonjoosung.BootCampRatingNet.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.programmers.kwonjoosung.BootCampRatingNet.user.utils.UserConverter.*;

@Service
public class UserServiceImpl implements UserService { // 닉네임으로 조회할 경우에 대한 메소드가 필요할까? 그때는 뭘 반환하는게 좋을까?

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(CreateUserRequest request) {
        return toResponse(userRepository.save(toUser(request)));
    }

    @Override
    public UserResponseDto findUserByUserId(UUID userId) {
        return toResponse(userRepository.findByUserId(userId.toString())
                .orElseThrow(() -> new DataNotFoundException("user", "user_id", userId.toString())));
    }

    @Override
    public UserResponseDto findByNickName(String nickName) {
        return toResponse(userRepository.findByNickName(nickName)
                .orElseThrow(() -> new DataNotFoundException("user", "nick_name", nickName)));
    }

    @Override
    public void deleteUser(UUID userId, String password) {
        userRepository.deleteUser(userId, password);
    }
}

