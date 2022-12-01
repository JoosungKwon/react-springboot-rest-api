package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.entity.BootCamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.programmers.kwonjoosung.BootCampRatingNet.exception.SqlFailMsgFormat.INSERT_FAIL;
import static com.programmers.kwonjoosung.BootCampRatingNet.exception.SqlFailMsgFormat.SELECT_FAIL;

@Repository
public class JdbcBootCampRepository implements BootCampRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcBootCampRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcBootCampRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toParamMap(BootCamp bootCamp) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("camp_id", bootCamp.getCampId().toString());
        paramMap.put("name", bootCamp.getName());
        paramMap.put("location", bootCamp.getLocation());
        paramMap.put("description", bootCamp.getDescription());
        return paramMap;
    }

    private final RowMapper<BootCamp> bootCampRowMapper = (rs, rowNum) ->
            BootCamp.builder()
                    .campId(UUID.fromString(rs.getString("camp_id")))
                    .name(rs.getString("name"))
                    .location(rs.getString("location"))
                    .description(rs.getString("description"))
                    .build();

    @Override
    public BootCamp save(BootCamp bootCamp) {
        final String sql = "INSERT INTO bootcamp (camp_id, name, location, description) " +
                "VALUES (:camp_id, :name, :location, :description)";
        try {
            jdbcTemplate.update(sql, toParamMap(bootCamp));
            return bootCamp;
        } catch (DuplicateKeyException e) {
            logger.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<BootCamp> findByCampId(UUID campId) {
        final String sql = "SELECT * FROM bootcamp WHERE camp_id = :camp_id";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    Map.of("camp_id", campId.toString()), bootCampRowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<BootCamp> findByCampName(String campName) {
        final String sql = "SELECT * FROM bootcamp WHERE name = :name";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    Map.of("name", campName), bootCampRowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<BootCamp> findAll() { // try-catch?
        final String sql = "SELECT * FROM bootcamp";
        return jdbcTemplate.query(sql, bootCampRowMapper);
    }

    @Override
    public void deleteByCampId(UUID campId) {
        final String sql = "DELETE FROM bootcamp WHERE camp_id = :camp_id";
        jdbcTemplate.update(sql, Map.of("camp_id", campId.toString()));
    }
}
