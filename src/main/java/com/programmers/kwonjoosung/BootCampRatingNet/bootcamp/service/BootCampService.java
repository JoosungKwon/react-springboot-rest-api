package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.service;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampInfoResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampWithReviewResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.CreateBootCampRequest;

import java.util.List;
import java.util.UUID;

public interface BootCampService {

    BootCampInfoResponse createBootCamp(CreateBootCampRequest request);

    BootCampWithReviewResponse findBootCampByCampId(UUID fromString);

    List<BootCampInfoResponse> getAllBootCamp();

    void deleteBootCampByCampId(UUID campId);

}
