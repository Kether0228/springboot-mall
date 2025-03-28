package com.kether.springbootmall.rowmapper;

import com.kether.springbootmall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreate_date(rs.getTimestamp("created_date"));
        user.setLast_modify_time(rs.getTimestamp("last_modified_date"));
        return user;
    }
}
