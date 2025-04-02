package com.kether.springbootmall.dao.Impl;

import com.kether.springbootmall.dao.OrderDao;
import com.kether.springbootmall.dto.OrderQueryParams;
import com.kether.springbootmall.dto.UserRequest;
import com.kether.springbootmall.model.Order;
import com.kether.springbootmall.model.OrderItem;
import com.kether.springbootmall.rowmapper.OrderItemRowMapper;
import com.kether.springbootmall.rowmapper.OrderRowMapper;
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

    @Override
    public void createOrderItem(Integer orderId,List<OrderItem> orderItemList) {
        String sql = "INSERT INTO order_item (order_id, product_id, quantity, amount)" +
                " VALUES (:order_id, :product_id, :quantity, :amount);";

        MapSqlParameterSource[] param = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            param[i] = new MapSqlParameterSource();
            param[i].addValue("order_id", orderId);
            param[i].addValue("product_id", orderItem.getProduct_id());
            param[i].addValue("quantity", orderItem.getQuantity());
            param[i].addValue("amount", orderItem.getAmount());

        }

        namedParameterJdbcTemplate.batchUpdate(sql,param);

    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id,user_id,total_amount,created_date,last_modified_date " +
                "FROM `order` WHERE order_id = :orderId;";
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("orderId", orderId);
        List<Order> orderList =  namedParameterJdbcTemplate.query(sql,param, new OrderRowMapper());

        if (orderList.size() > 0) {
            return orderList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemListByOrderId(Integer orderId) {

        String sql = "SELECT o.order_item_id,o.order_id,o.product_id,o.quantity,o.amount,p.product_name,p.image_url " +
                " FROM order_item AS o " +
                " LEFT JOIN product AS p USING (product_id) " +
                " WHERE order_id = :orderId";

        Map <String,Object> params = new HashMap<>();
        params.put("orderId", orderId);
        List<OrderItem> orderItemList =  namedParameterJdbcTemplate.query(sql,new MapSqlParameterSource(params), new OrderItemRowMapper());
            return orderItemList;
    }

    @Override
    public List<Order> getOrder(OrderQueryParams params) {
        String sql = "SELECT order_id,user_id,total_amount,created_date,last_modified_date FROM " +
                "`order` WHERE user_id = :userId LIMIT :limit OFFSET :offset";
        Map <String,Object> map = new HashMap<>();
        map.put("userId", params.getUserId());
        map.put("limit", params.getLimit());
        map.put("offset", params.getOffset());
        List<Order> orderList =  namedParameterJdbcTemplate.query(sql,map, new OrderRowMapper());
        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams params) {
        String sql = "SELECT count(order_id) FROM `order` WHERE user_id = :userId";
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", params.getUserId());
        return namedParameterJdbcTemplate.queryForObject(sql,paramsMap,Integer.class);
    }


}
