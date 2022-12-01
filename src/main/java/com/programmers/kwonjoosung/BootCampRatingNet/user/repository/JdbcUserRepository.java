package com.programmers.kwonjoosung.BootCampRatingNet.user.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.Email;
import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.User;
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
public class JdbcUserRepository implements UserRepository {
    
    private final NamedParameterJdbcTemplate jdbcTemplate;

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

    @Override
    public User save(User user) {
        final String sql = "INSERT INTO user (user_id, nick_name, email, password, phone, address) " +
                "VALUES (:user_id, :nick_name, :email, :password, :phone, :address)";
        try {
            jdbcTemplate.update(sql, toParamMap(user));
            return user;
        } catch (DuplicateKeyException e) {
            log.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        final String sql = "SELECT * FROM user WHERE user_id = :user_id";
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    Map.of("user_id",userId),userRowMapper));
        } catch (EmptyResultDataAccessException e){
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByNickName(String nickName) {
        final String sql = "SELECT * FROM user WHERE nick_name = :nick_name";
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    Map.of("nick_name",nickName),userRowMapper));
        } catch (EmptyResultDataAccessException e){
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void deleteUser(UUID userId, String password) {
        final String sql = "DELETE FROM user WHERE user_id = :user_id AND password = :password";
        jdbcTemplate.update(sql,
                Map.of("user_id",userId.toString(),"password",password));
    }
}
