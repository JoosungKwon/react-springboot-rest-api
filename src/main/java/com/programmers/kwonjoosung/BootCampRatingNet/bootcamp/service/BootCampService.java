package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.service;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampResponseDto;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.CreateBootCampRequest;

import java.util.List;
import java.util.UUID;

public interface BootCampService {

    BootCampResponseDto save(CreateBootCampRequest request);

//    BootCampResponseDto findByCampId(UUID campId); 더 많은 정보를 주고 싶을땐 어떻게 해야하나?

    BootCampResponseDto findByCampName(String campName);

//    List<BootCampResponseDto> findByRating(int rating); // TODO: 추후에 추가

    List<BootCampResponseDto> getAllBootCamp();

    void deleteByCampId(UUID campId);

}
