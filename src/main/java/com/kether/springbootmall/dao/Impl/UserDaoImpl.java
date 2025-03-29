package com.kether.springbootmall.dao.Impl;

import com.kether.springbootmall.dao.UserDao;
import com.kether.springbootmall.dto.UserRequest;
import com.kether.springbootmall.model.User;
import com.kether.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRequest userRequest) {
        String sql = "INSERT INTO `user`(email, password, created_date, last_modified_date) " +
                "VALUES (:email, :password, :created_date, :last_modified_date)";
        Map<String, Object> params = new HashMap<>();
        params.put("email", userRequest.getEmail());
        params.put("password", userRequest.getPassword());
        Date now = new Date();
        params.put("created_date", now);
        params.put("last_modified_date", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(params),keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id,email, password, created_date, last_modified_date " +
                "FROM `user` WHERE email = :email";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);

        List<User> user = namedParameterJdbcTemplate.query(sql,new MapSqlParameterSource(params),new UserRowMapper());

        if (user.size()>0) {
            return user.get(0);
        }else
            return null;
    }

    @Override
    public User getUserById(Integer user_id) {
        String sql = "SELECT user_id,email, password, created_date, last_modified_date " +
                "FROM `user` WHERE user_id = :user_id";
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);

        List<User> user = namedParameterJdbcTemplate.query(sql,new MapSqlParameterSource(params),new UserRowMapper());

        if (user.size()>0) {
            return user.get(0);
        }else
            return null;
    }

}
