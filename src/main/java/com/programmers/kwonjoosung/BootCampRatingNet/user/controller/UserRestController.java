package com.programmers.kwonjoosung.BootCampRatingNet.user.controller;


import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.CreateUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.DeleteUserRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.user.dto.UserResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RestController
public class UserRestController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> findByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @GetMapping(value = "/nickName/{nickName}")
    public ResponseEntity<UserResponse> findByNickName(@PathVariable String nickName) {
        return ResponseEntity.ok(userService.findByNickName(nickName));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@RequestBody DeleteUserRequest request) {
        userService.deleteUser(request);
        return ResponseEntity.ok().build();
    }
}
