package com.kether.springbootmall.dao;

import com.kether.springbootmall.model.OrderItem;
import com.kether.springbootmall.model.Product;

public interface OrderDao {
    Integer createOrder(Integer userId,Integer totalAmount);
    Integer createOrderItem(OrderItem orderItem);

}
