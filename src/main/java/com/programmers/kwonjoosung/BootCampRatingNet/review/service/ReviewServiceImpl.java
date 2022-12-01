package com.programmers.kwonjoosung.BootCampRatingNet.review.service;

import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataNotFoundException;
import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.CreateReviewRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.ReviewResponseDto;
import com.programmers.kwonjoosung.BootCampRatingNet.review.repository.ReviewRepository;
import com.programmers.kwonjoosung.BootCampRatingNet.review.utils.ReviewConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.programmers.kwonjoosung.BootCampRatingNet.review.utils.ReviewConverter.toResponse;
import static com.programmers.kwonjoosung.BootCampRatingNet.review.utils.ReviewConverter.toReview;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewResponseDto save(CreateReviewRequest request) {
        return toResponse(reviewRepository.save(toReview(request)));
    }

    @Override
    public ReviewResponseDto findByNickName(String nickName) {
        return toResponse(reviewRepository.findByNickName(nickName)
                .orElseThrow(() -> new DataNotFoundException("review", "user_nick_name", nickName)));
    }

    @Override
    public ReviewResponseDto findByCampName(String campName) {
        return toResponse(reviewRepository.findByCampName(campName)
                .orElseThrow(() -> new DataNotFoundException("review", "camp_name", campName)));
    }

    @Override
    public List<ReviewResponseDto> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewConverter::toResponse)
                .toList();
    }

    @Override
    public void deleteByReviewId(UUID reviewId) {
        reviewRepository.deleteByReviewId(reviewId);
    }
}
