package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.model.BootCamp;
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
public class JdbcBootCampRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(JdbcBootCampRepository.class);

    private static final int FAIL = 0;

    public JdbcBootCampRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toParamMap(BootCamp bootCamp) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("camp_id", bootCamp.getCampId());
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

    public BootCamp save(BootCamp bootCamp) {
        try {
            jdbcTemplate.update("INSERT INTO bootcamp (camp_id, name, location, description) " +
                    "VALUES (:camp_id, :name, :location, :description)", toParamMap(bootCamp));
            return bootCamp;
        } catch (DuplicateKeyException e) {
            logger.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw e;
        }
    }

    public Optional<BootCamp> findByCampId(String campId) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM bootcamp WHERE camp_id = :camp_id",
                    Map.of("camp_id",campId),bootCampRowMapper));
        } catch (EmptyResultDataAccessException e){
            logger.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<BootCamp> findByCampName(String campName) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM bootcamp WHERE name = :name",
                    Map.of("name",campName),bootCampRowMapper));
        } catch (EmptyResultDataAccessException e){
            logger.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    public List<BootCamp> findAll() { // try-catch?
        return jdbcTemplate.query("SELECT * FROM bootcamp", bootCampRowMapper);
    }
}
