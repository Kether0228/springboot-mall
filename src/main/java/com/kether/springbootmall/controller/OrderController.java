package com.kether.springbootmall.controller;

import com.kether.springbootmall.dto.CreateOrderRequest;
import com.kether.springbootmall.model.Order;
import com.kether.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/user/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId
                                        ,@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        Integer orderId = orderService.createOrder(userId, createOrderRequest);
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }


}
