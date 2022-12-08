package com.programmers.kwonjoosung.BootCampRatingNet.review.service;

import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.CreateReviewRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.ReviewResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.review.repository.ReviewRepository;
import com.programmers.kwonjoosung.BootCampRatingNet.review.utils.ReviewConverter;
import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.User;
import com.programmers.kwonjoosung.BootCampRatingNet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.programmers.kwonjoosung.BootCampRatingNet.review.utils.ReviewConverter.toResponse;
import static com.programmers.kwonjoosung.BootCampRatingNet.review.utils.ReviewConverter.toReview;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewResponse createReview(CreateReviewRequest request) {
        UUID userId = checkUser(request.getUserNickName(), request.getUserPassword());
        return toResponse(reviewRepository.save(toReview(request, userId)));
    }

    @Override
    public List<ReviewResponse> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewConverter::toResponse)
                .toList();
    }

    @Override
    public void deleteByReviewId(UUID reviewId) {
        reviewRepository.deleteByReviewId(reviewId);
    }

    private UUID checkUser(String userNickName, String userPassword) {
        User user = userRepository.findByNickNameAndPassword(userNickName, userPassword)
                .orElseThrow(() -> new IllegalArgumentException("닉네임 혹은 비밀번호가 일치하지 않습니다."));
        return user.getUserId();
    }
}
