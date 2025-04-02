package com.kether.springbootmall.service;

import com.kether.springbootmall.dto.CreateOrderRequest;
import com.kether.springbootmall.dto.OrderQueryParams;
import com.kether.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    List<Order> getOrder(OrderQueryParams params);
    Integer countOrder(OrderQueryParams params);

}
