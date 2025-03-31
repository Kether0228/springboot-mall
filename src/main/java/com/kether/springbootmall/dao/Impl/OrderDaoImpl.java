package com.kether.springbootmall.dao.Impl;

import com.kether.springbootmall.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId,Integer totalAmount) {
        String sql = "INSERT INTO `order` (user_id, total_amount,created_date,last_modified_date) " +
                " VALUES (:user_id, :total_amount, :created_date, :last_modified_date);";
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("user_id", userId);
        param.put("total_amount", totalAmount);
        Date now = new Date();
        param.put("created_date", now);
        param.put("last_modified_date", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(param), keyHolder);


        return keyHolder.getKey().intValue();
    }
}
