package com.programmers.kwonjoosung.BootCampRatingNet.review.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataAlreadyExistException;
import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataNotFoundException;
import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.programmers.kwonjoosung.BootCampRatingNet.exception.SqlFailMsgFormat.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class JdbcReviewRepository implements ReviewRepository {

    private static final int FAIL = 0;
    private static final RowMapper<Review> reviewRowMapper = (rs, rowNum) ->
            Review.builder()
                    .reviewId(UUID.fromString(rs.getString("review_id")))
                    .title(rs.getString("title"))
                    .rating(rs.getInt("rating"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .build();
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static Map<String, Object> toParamMap(Review review) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("review_id", review.getReviewId().toString());
        paramMap.put("title", review.getTitle());
        paramMap.put("rating", review.getRating());
        paramMap.put("created_at", review.getCreatedAt());
        return paramMap;
    }

    private static RowMapper<Double> getRatingRowMapper() {
        return (rs, rowNum) -> {
            double count = rs.getLong("count");
            double sum = rs.getLong("sum");
            if (count == 0) {
                return 0.0;
            }
            return sum / count;
        };
    }

    @Override
    public Review save(Review review) {
        final String sql = "INSERT INTO camp_review (review_id, user_id, camp_id, title, content, rating,created_at) " +
                "VALUES (:review_id, :user_id, :camp_id, :title, :content, :rating, :created_at)";
        try {
            jdbcTemplate.update(sql, toParamMap(review));
            return review;
        } catch (DuplicateKeyException e) {
            log.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw new DataAlreadyExistException("review", "review_id", review.getReviewId().toString());
        }
    }

    @Override
    public List<Review> findByReviewId(UUID reviewId) {
        final String sql = "SELECT * FROM camp_review WHERE review_id = :review_id order by created_at desc";
        try {
            return jdbcTemplate.query(sql, Map.of("review_id", reviewId.toString()), reviewRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<Review> findByCampId(UUID campId) {
        final String sql = "SELECT * FROM camp_review WHERE camp_id = :camp_id order by created_at desc";
        try {
            return jdbcTemplate.query(sql, Map.of("camp_id", campId.toString()), reviewRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<Review> findByUserId(UUID userId) {
        final String sql = "SELECT * FROM camp_review WHERE user_id = :user_id order by created_at desc";
        try {
            return jdbcTemplate.query(sql, Map.of("user_id", userId.toString()), reviewRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<Review> findAll() { // try-catch?
        final String sql = "SELECT * FROM camp_review order by created_at desc";
        try {
            return jdbcTemplate.query(sql, reviewRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return List.of();
        }
    }

    @Override
    public void deleteByReviewId(UUID reviewID) {
        final String sql = "DELETE FROM camp_review WHERE review_id = :review_id";
        int deleteResult = jdbcTemplate.update(sql, Map.of("review_id", reviewID.toString()));
        if (deleteResult == FAIL) {
            log.error(DELETE_FAIL.getMessage(), "해당하는 리뷰id를 찾을 수 없습니다.");
            throw new DataNotFoundException("review", "review_id", reviewID.toString());
        }
    }

    @Override
    public Double findRatingByCampId(UUID campId) {
        final String sql = "SELECT COUNT(*) AS count, SUM(rating) AS sum FROM camp_review WHERE camp_id = :camp_id";
        return jdbcTemplate.queryForObject(sql, Map.of("camp_id", campId.toString()), getRatingRowMapper());
    }
}
