package com.programmers.kwonjoosung.BootCampRatingNet.review.service;

import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.CreateReviewRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.ReviewResponseDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewResponseDto save(CreateReviewRequest request);

    ReviewResponseDto findByNickName(String nickName);

    ReviewResponseDto findByCampName(String campName);

    List<ReviewResponseDto> findAll();

    void deleteByReviewId(UUID reviewId);
}
