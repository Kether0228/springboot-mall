package com.kether.springbootmall.service.Impl;

import com.kether.springbootmall.dao.OrderDao;
import com.kether.springbootmall.dao.ProductDao;
import com.kether.springbootmall.dao.UserDao;
import com.kether.springbootmall.dto.BuyItem;
import com.kether.springbootmall.dto.CreateOrderRequest;
import com.kether.springbootmall.dto.OrderQueryParams;
import com.kether.springbootmall.model.Order;
import com.kether.springbootmall.model.OrderItem;
import com.kether.springbootmall.model.Product;
import com.kether.springbootmall.model.User;
import com.kether.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest  createOrderRequest) {

        User user = userDao.getUserById(userId);
        //檢查userId是否存在
        if (user == null) {
            logger.warn("使用者 {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();


        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {

            Product product = productDao.getProductById(buyItem.getProductId());
            //檢查商品與庫存
            if (product == null) {
                logger.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else{
                if ( product.getStock() < buyItem.getQuantity() ) {
                    logger.warn("商品 {} 庫存數量不足，無法購買", product.getStock());
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
            }

            //扣除商品庫存
            productDao.updateStock(product.getProduct_id(), product.getStock() - buyItem.getQuantity());

            //計算總價格
            int price = product.getPrice() * buyItem.getQuantity();
            totalAmount += price;

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct_id(product.getProduct_id());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(price);
            orderItemList.add(orderItem);
        }
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItem(orderId,orderItemList);


        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        order.setOrder_items(orderDao.getOrderItemListByOrderId(orderId));
        return order;
    }

    @Override
    public List<Order> getOrder(OrderQueryParams params) {
        List<Order> orderList =  orderDao.getOrder(params);
        for (Order order : orderList) {
            order.setOrder_items(orderDao.getOrderItemListByOrderId(order.getOrder_id()));
        }
        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams params) {
        return orderDao.countOrder(params);
    }

}
