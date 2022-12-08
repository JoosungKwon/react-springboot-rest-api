package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.entity.BootCamp;
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

import java.util.*;

import static com.programmers.kwonjoosung.BootCampRatingNet.exception.SqlFailMsgFormat.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class JdbcBootCampRepository implements BootCampRepository {

    private static final int FAIL = 0;
    private static final RowMapper<BootCamp> bootCampRowMapper = (rs, rowNum) ->
            BootCamp.builder()
                    .campId(UUID.fromString(rs.getString("camp_id")))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .image(rs.getString("image"))
                    .build();
    private static final RowMapper<Review> reviewRowMapper = (rs, rowNum) ->
            Review.builder()
                    .reviewId(UUID.fromString(rs.getString("review_id")))
                    .campId(UUID.fromString(rs.getString("camp_id")))
                    .userId(UUID.fromString(rs.getString("user_id")))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .rating(rs.getInt("rating"))
                    .build();

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static Map<String, Object> toParamMap(BootCamp bootCamp) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("camp_id", bootCamp.getCampId().toString());
        paramMap.put("name", bootCamp.getName());
        paramMap.put("description", bootCamp.getDescription());
        paramMap.put("image", bootCamp.getImage());
        return paramMap;
    }

    @Override
    public BootCamp save(BootCamp bootCamp) {
        final String sql = "INSERT INTO bootcamp (camp_id, name, description, image) " +
                "VALUES (:camp_id, :name, :description, :image)";
        try {
            jdbcTemplate.update(sql, toParamMap(bootCamp));
            return bootCamp;
        } catch (DuplicateKeyException e) {
            log.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw new DataAlreadyExistException("bootcamp", "camp_id", bootCamp.getCampId().toString());
        }
    }

    @Override
    public Optional<BootCamp> findByCampIdWithReviews(UUID campId) {
        final String sql = "SELECT * FROM bootcamp AS B JOIN camp_review AS C ON B.camp_id = C.camp_id " +
                "WHERE B.camp_id = :camp_id order by C.created_at desc";
        try {
            return Optional.ofNullable(jdbcTemplate.query(sql, Collections.singletonMap("camp_id", campId.toString()),
                    rs -> {
                        List<Review> reviews = new ArrayList<>();
                        BootCamp bootCamp = null;
                        int row = 0;
                        while (rs.next()) {
                            bootCamp = bootCampRowMapper.mapRow(rs, row);
                            reviews.add(reviewRowMapper.mapRow(rs, row));
                            row++;
                        }
                        if (bootCamp != null) {
                            bootCamp.setReviews(reviews);
                        }
                        return bootCamp;
                    }));
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<BootCamp> findAll() {
        final String sql = "SELECT * FROM bootcamp";
        try {
            return jdbcTemplate.query(sql, bootCampRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteByCampId(UUID campId) {
        final String sql = "DELETE FROM bootcamp WHERE camp_id = :camp_id";
        int deleteResult = jdbcTemplate.update(sql, Map.of("camp_id", campId.toString()));
        if (deleteResult == FAIL) {
            log.error(DELETE_FAIL.getMessage(), "해당하는 캠프id를 찾을 수 없습니다.");
            throw new DataNotFoundException("bootcamp", "camp_id", campId.toString());
        }
    }
}
