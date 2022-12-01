package com.programmers.kwonjoosung.BootCampRatingNet.review.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.review.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.programmers.kwonjoosung.BootCampRatingNet.exception.SqlFailMsgFormat.INSERT_FAIL;
import static com.programmers.kwonjoosung.BootCampRatingNet.exception.SqlFailMsgFormat.SELECT_FAIL;

@Slf4j
@Repository
public class JdbcReviewRepository implements ReviewRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcReviewRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Map<String, Object> toParamMap(Review review) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("review_id", review.getReviewId().toString());
        paramMap.put("camp_name", review.getCampName());
        paramMap.put("user_nick_name", review.getUserNickName());
        paramMap.put("title", review.getTitle());
        paramMap.put("comment", review.getComment());
        paramMap.put("rating", review.getRating());
        paramMap.put("created_at", review.getCreatedAt());
        paramMap.put("updated_at", review.getUpdatedAt());
        return paramMap;
    }

    private static final RowMapper<Review> reviewRowMapper = (rs, rowNum) ->
            Review.builder()
                    .reviewId(UUID.fromString(rs.getString("review_id")))
                    .campName(rs.getString("camp_name"))
                    .userNickName(rs.getString("user_nick_name"))
                    .title(rs.getString("title"))
                    .comment(rs.getString("comment"))
                    .rating(rs.getInt("rating"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                    .build();

    @Override
    public Review save(Review review) {
        try {
            final String sql = "INSERT INTO camp_review (review_id, camp_name, user_nick_name, title, comment, rating,created_at, updated_at) " +
                    "VALUES (:review_id, :camp_name, :user_nick_name, :title, :comment, :rating, :created_at, :updated_at)";
            jdbcTemplate.update(sql, toParamMap(review));
            return review;
        } catch (DuplicateKeyException e) {
            log.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Review> findByNickName(String nickName) {
        final String sql = "SELECT * FROM camp_review WHERE user_nick_name = :user_nick_name";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    Map.of("user_nick_name", nickName), reviewRowMapper));
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Review> findByCampName(String campName) {
        final String sql = "SELECT * FROM camp_review WHERE camp_name = :camp_name";
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql,
                            Map.of("camp_name", campName), reviewRowMapper));
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Review> findAll() { // try-catch?
        final String sql = "SELECT * FROM camp_review";
        return jdbcTemplate.query(sql, reviewRowMapper);
    }

    @Override
    public void deleteByReviewId(UUID reviewID) {
        final String sql = "DELETE FROM camp_review WHERE review_id = :review_id";
        jdbcTemplate.update(sql, Map.of("review_id", reviewID.toString()));
    }
}
