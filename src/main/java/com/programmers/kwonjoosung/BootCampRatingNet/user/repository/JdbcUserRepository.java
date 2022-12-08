package com.programmers.kwonjoosung.BootCampRatingNet.user.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataAlreadyExistException;
import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataNotFoundException;
import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.Email;
import com.programmers.kwonjoosung.BootCampRatingNet.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.programmers.kwonjoosung.BootCampRatingNet.exception.SqlFailMsgFormat.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class JdbcUserRepository implements UserRepository {

    private static final int FAIL = 0;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper = (rs, rowNum) ->
            User.builder()
                    .userId(UUID.fromString(rs.getString("user_id")))
                    .nickName(rs.getString("nick_name"))
                    .email(new Email(rs.getString("email")))
                    .password(rs.getString("password"))
                    .phone(rs.getString("phone"))
                    .bootCamp(rs.getString("bootcamp"))
                    .build();

    private Map<String, Object> toParamMap(User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", user.getUserId().toString());
        paramMap.put("nick_name", user.getNickName());
        paramMap.put("email", user.getEmail().getAddress());
        paramMap.put("password", user.getPassword());
        paramMap.put("phone", user.getPhone());
        paramMap.put("bootcamp", user.getBootCamp());
        return paramMap;
    }

    @Override
    public User save(User user) {
        final String sql = "INSERT INTO user (user_id, nick_name, email, password, phone, bootcamp) " +
                "VALUES (:user_id, :nick_name, :email, :password, :phone, :bootcamp )";
        try {
            jdbcTemplate.update(sql, toParamMap(user));
            return user;
        } catch (DuplicateKeyException e) {
            log.error(INSERT_FAIL.getMessage(), e.getMessage());
            throw new DataAlreadyExistException("user", "user_id", user.getUserId().toString());
        }
    }

    @Override
    public Optional<User> findByUserId(UUID userId) {
        final String sql = "SELECT * FROM user WHERE user_id = :user_id";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    Map.of("user_id", userId.toString()), userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByNickName(String nickName) {
        final String sql = "SELECT * FROM user WHERE nick_name = :nick_name";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    Map.of("nick_name", nickName), userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void deleteUser(UUID userId, String password) {
        final String sql = "DELETE FROM user WHERE user_id = :user_id AND password = :password";
        int deleteResult = jdbcTemplate.update(sql,
                Map.of("user_id", userId.toString(), "password", password));
        if (deleteResult == FAIL) {
            log.error(DELETE_FAIL.getMessage(), "아이디 혹은 비밀번호가 일치하지 않습니다.");
            throw new DataNotFoundException("user", "user_id or password", userId + " or " + password);
        }
    }

    @Override
    public Optional<User> findByNickNameAndPassword(String NickName, String password) {
        final String sql = "SELECT * FROM user WHERE nick_name = :nick_name AND password = :password";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    Map.of("nick_name", NickName, "password", password), userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            log.warn(SELECT_FAIL.getMessage(), e.getMessage());
            return Optional.empty();
        }
    }
}
