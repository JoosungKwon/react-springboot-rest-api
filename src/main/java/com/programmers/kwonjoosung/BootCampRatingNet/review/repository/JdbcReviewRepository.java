package com.programmers.kwonjoosung.BootCampRatingNet.review.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.review.model.Review;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.programmers.kwonjoosung.BootCampRatingNet.error.SqlErrorMsgFormat.INSERT_FAIL;
import static com.programmers.kwonjoosung.BootCampRatingNet.error.SqlErrorMsgFormat.SELECT_FAIL;

@Repository
public class JdbcReviewRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(JdbcReviewRepository.class);

    public JdbcReviewRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toParamMap(Review review) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("review_id", review.getReviewId());
        paramMap.put("user_id", review.getUserId());
        paramMap.put("camp_id", review.getCampId());
        paramMap.put("title", review.getTitle());
        paramMap.put("content", review.getComment());
        paramMap.put("rating", review.getRating());
        paramMap.put("created_at", review.getCreatedAt());
        paramMap.put("updated_at", review.getUpdatedAt());
        return paramMap;
    }

    private final RowMapper<Review> reviewRowMapper = (rs, rowNum) ->
            Review.builder()
                    .reviewId(UUID.fromString(rs.getString("review_id")))
                    .campId(UUID.fromString(rs.getString("camp_name")))
                    .userId(UUID.fromString(rs.getString("user_nick_name")))
                    .title(rs.getString("title"))
                    .comment(rs.getString("comment"))
                    .rating(rs.getInt("rating"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                    .build();

    public Review save(Review review) {
        try {
            jdbcTemplate.update("INSERT INTO camp_review (review_id, camp_name, user_nick_name, title, comment, rating,created_at, updated_at) " +
                    "VALUES (:review_id, :camp_name, :user_nick_name, :title, :comment, :rating, :created_at, :updated_at)", toParamMap(review));
            return review;
        } catch (DuplicateKeyException e) {
            logger.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw e;
        }
    }

    public Optional<Review> findByNickName(String nickName) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM camp_review WHERE user_nick_name = :user_nick_name",
                    Map.of("user_nick_name",nickName),reviewRowMapper));
        } catch (EmptyResultDataAccessException e){
            logger.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Review> findByCampName(String campName) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM camp_review WHERE camp_name = :camp_name",
                    Map.of("camp_name",campName),reviewRowMapper));
        } catch (EmptyResultDataAccessException e){
            logger.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    public List<Review> findAll() { // try-catch?
        return jdbcTemplate.query("SELECT * FROM camp_review", reviewRowMapper);
    }
}
