package com.programmers.kwonjoosung.BootCampRatingNet.review.controller;

import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.CreateReviewRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.review.dto.ReviewResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.review.service.ReviewService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/api/v1/reviews",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RestController
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping()
    public ResponseEntity<ReviewResponse> createReview(@RequestBody CreateReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @GetMapping()
    public ResponseEntity<List<ReviewResponse>> findAll() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @DeleteMapping(value = "/{reviewId}")
    public ResponseEntity<ReviewResponse> deleteByReviewId(@PathVariable String reviewId) {
        reviewService.deleteByReviewId(UUID.fromString(reviewId));
        return ResponseEntity.accepted().build();
    }

}




