package com.programmers.kwonjoosung.BootCampRatingNet.user.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUserId(String userId);

    Optional<User> findByNickName(String nickName);

    void deleteUser(UUID userId, String password);

}
