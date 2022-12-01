package com.programmers.kwonjoosung.BootCampRatingNet.user.model;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.model.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;


@Builder
@Getter
public class User {
    private final UUID userId;
    private final String nickName;
    private final String password;
    private final Email email;
    private final String phone;
    private final String address;
    private String bootCamp;
    private List<Review> reviews;
}
