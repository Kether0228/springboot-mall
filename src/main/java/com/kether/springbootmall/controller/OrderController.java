package com.kether.springbootmall.controller;

import com.kether.springbootmall.dto.CreateOrderRequest;
import com.kether.springbootmall.dto.OrderQueryParams;
import com.kether.springbootmall.model.Order;
import com.kether.springbootmall.model.User;
import com.kether.springbootmall.service.OrderService;
import com.kether.springbootmall.service.UserService;
import com.kether.springbootmall.util.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Operation(
            summary = "建立訂單",
            description = "使用者可透過此 API 提交訂單資訊，建立新的訂單。"
    )
    @PostMapping("/user/{userId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Integer userId
                                        ,@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        Integer orderId = orderService.createOrder(userId, createOrderRequest);
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @Operation(
            summary = "取得使用者的訂單列表",
            description = "根據使用者 ID，取得該使用者的歷史訂單清單。可使用 limit 和 offset 參數做分頁查詢。"
    )
    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId
                                                 ,@RequestParam(defaultValue = "5") @Max(20) @Min(1) Integer limit
                                                 ,@RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        //檢查userId是否存在
        User user = userService.getUserById(userId);

        if (user == null) {
            logger.warn("使用者ID{}不存在", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        //查詢order
        List<Order> orderList =  orderService.getOrder(orderQueryParams);
        //查詢order總數
        Integer orderTotal = orderService.countOrder(orderQueryParams);

        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(orderTotal);
        page.setList(orderList);


        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


}
