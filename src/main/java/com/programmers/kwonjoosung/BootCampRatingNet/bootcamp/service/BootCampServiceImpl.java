package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.service;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampInfoResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampWithReviewResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.CreateBootCampRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.repository.BootCampRepository;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.utils.BootCampConverter;
import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataNotFoundException;
import com.programmers.kwonjoosung.BootCampRatingNet.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.utils.BootCampConverter.toBootCamp;
import static com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.utils.BootCampConverter.toResponseWithReview;

@RequiredArgsConstructor
@Service
public class BootCampServiceImpl implements BootCampService {

    private final BootCampRepository bootCampRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public BootCampInfoResponse createBootCamp(CreateBootCampRequest request) {
        return BootCampConverter.toInfoResponse(bootCampRepository.save(toBootCamp(request)));
    }

    @Override
    public List<BootCampInfoResponse> getAllBootCamp() {
        return bootCampRepository.findAll()
                .stream()
                .map((bootCamp) -> BootCampConverter
                        .toInfoResponse(bootCamp,reviewRepository.findRatingByCampId(bootCamp.getCampId())))
                .toList();
    }

    @Override
    public void deleteBootCampByCampId(UUID campId) {
        bootCampRepository.deleteByCampId(campId);
    }

    @Override
    public BootCampWithReviewResponse findBootCampByCampId(UUID campId) {
        return toResponseWithReview(bootCampRepository.findByCampIdWithReviews(campId)
                .orElseThrow(() -> new DataNotFoundException("bootcamp", "camp_id", campId.toString())));
    }


}
