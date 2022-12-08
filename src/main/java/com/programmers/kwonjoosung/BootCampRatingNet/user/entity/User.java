package com.programmers.kwonjoosung.BootCampRatingNet.user.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Builder
@Getter
public class User {
    private UUID userId;
    private String nickName;
    private String password;
    private Email email;
    private String phone;
    private String address;
    private String bootCamp;
}
