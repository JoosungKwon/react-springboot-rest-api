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
    private final String userName;
    private final String userPassword;
    private final Email userEmail;
    private final String userPhone;
    private final String userAddress;
    private String bootCamp;
    private List<Review> reviews;
}
