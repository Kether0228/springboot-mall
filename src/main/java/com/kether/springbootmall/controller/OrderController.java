package com.kether.springbootmall.controller;

import com.kether.springbootmall.dto.CreateOrderRequest;
import com.kether.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/user/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userID
                                        ,@RequestBody @Valid CreateOrderRequest createOrderRequest) {

    }
}
