package com.programmers.kwonjoosung.BootCampRatingNet.user.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Builder
@Getter
public class User { // 여기서 final이 가지는 의미는 뭘까?
    private final UUID userId;
    private final String nickName;
    private final String password;
    private final Email email;
    private final String phone;
    private String address;
    private String bootCamp;
}
