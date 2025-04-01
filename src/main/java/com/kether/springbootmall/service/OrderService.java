package com.kether.springbootmall.service;

import com.kether.springbootmall.dto.CreateOrderRequest;
import com.kether.springbootmall.model.Order;
import com.kether.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);

}
