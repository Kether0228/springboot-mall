package com.kether.springbootmall.service.Impl;

import com.kether.springbootmall.dao.OrderDao;
import com.kether.springbootmall.dao.ProductDao;
import com.kether.springbootmall.dto.BuyItem;
import com.kether.springbootmall.dto.CreateOrderRequest;
import com.kether.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    public Integer createOrder(Integer userId, CreateOrderRequest  createOrderRequest) {
        int totalAmount = 0;
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            int price = product.getPrice() * buyItem.getQuantity();
            totalAmount += price;
        }
        Integer orderId = orderDao.createOrder(userId, totalAmount);
        return orderId;
    }
}
