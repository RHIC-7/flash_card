package com.flash_card.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.flash_card.demo.entity.Users;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    };

    @Override
    public int insertUser(Users users) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                users.getUsername(),
                users.getEmail());
    };

    @Override
    public int updateUserInfo(Users users) {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                users.getUsername(),
                users.getEmail(),
                users.getId());
    };

    @Override
    public Users selectUser(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<Map<String, Object>> categoryListObject = jdbcTemplate.queryForList(sql, new Object[] { email });
        List<Users> users = categoryListObject.stream().map(rs -> new Users(
                (Integer) rs.get("id"),
                (String) rs.get("username"),
                (String) rs.get("email"),
                (LocalDateTime) rs.get("created_at"))).collect(Collectors.toList());

        return users.isEmpty() ? null : users.get(0);
    };

}