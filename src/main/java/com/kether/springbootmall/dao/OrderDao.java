package com.kether.springbootmall.dao;

import com.kether.springbootmall.dto.OrderQueryParams;
import com.kether.springbootmall.model.Order;
import com.kether.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId,Integer totalAmount);
    void createOrderItem(Integer orderId,List<OrderItem> orderItemList);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemListByOrderId(Integer orderId);
    List<Order> getOrder(OrderQueryParams params);
    Integer countOrder(OrderQueryParams params);

}
