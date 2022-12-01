package com.programmers.kwonjoosung.BootCampRatingNet.user.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.user.model.Email;
import com.programmers.kwonjoosung.BootCampRatingNet.user.model.User;
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
public class JdbcUserRepository {
    
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(JdbcUserRepository.class);
    

    
    public JdbcUserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toParamMap(User user) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("user_id", user.getUserId());
        paramMap.put("nick_name", user.getNickName());
        paramMap.put("email", user.getEmail());
        paramMap.put("password", user.getPassword());
        paramMap.put("phone", user.getPhone());
        paramMap.put("address", user.getAddress());
        return paramMap;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) ->
            User.builder()
                    .userId(UUID.fromString(rs.getString("user_id")))
                    .nickName(rs.getString("nick_name"))
                    .email(new Email(rs.getString("email")))
                    .password(rs.getString("password"))
                    .phone(rs.getString("phone"))
                    .address(rs.getString("address"))
                    .build();

    public User save(User user) {
        try {
            jdbcTemplate.update("INSERT INTO user (user_id, nick_name, email, password, phone, address) " +
                    "VALUES (:user_id, :nick_name, :email, :password, :phone, :address)", toParamMap(user));
            return user;
        } catch (DuplicateKeyException e) {
            logger.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw e;
        }
    }

    public Optional<User> findByUserId(String userId) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM user WHERE user_id = :user_id",
                    Map.of("user_id",userId),userRowMapper));
        } catch (EmptyResultDataAccessException e){
            logger.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<User> findByNickName(String nickName) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM user WHERE nick_name = :nick_name",
                    Map.of("nick_name",nickName),userRowMapper));
        } catch (EmptyResultDataAccessException e){
            logger.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    public List<User> findAll() { // try-catch?
        return jdbcTemplate.query("SELECT * FROM user", userRowMapper);
    }
}
