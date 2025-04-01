package com.kether.springbootmall.service.Impl;

import com.kether.springbootmall.dao.OrderDao;
import com.kether.springbootmall.dao.ProductDao;
import com.kether.springbootmall.dto.BuyItem;
import com.kether.springbootmall.dto.CreateOrderRequest;
import com.kether.springbootmall.model.OrderItem;
import com.kether.springbootmall.model.Product;
import com.kether.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest  createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            int price = product.getPrice() * buyItem.getQuantity();
            totalAmount += price;

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct_id(product.getProduct_id());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(price);

            orderItemList.add(orderItem);
        }
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrder_id(orderId);
            orderDao.createOrderItem(orderItem);
        }

        return orderId;
    }
}
