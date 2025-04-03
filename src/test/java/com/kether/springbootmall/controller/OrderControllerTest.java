package com.kether.springbootmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kether.springbootmall.dto.BuyItem;
import com.kether.springbootmall.dto.CreateOrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("createOrder-正常建立訂單")
    void createOrder_Sucess() throws Exception {
        List<BuyItem> list = new ArrayList<>();
        BuyItem buyItem1 = new BuyItem();
        BuyItem buyItem2 = new BuyItem();
        buyItem1.setProductId(1);
        buyItem1.setQuantity(2);
        buyItem2.setProductId(4);
        buyItem2.setQuantity(1);
        list.add(buyItem1);
        list.add(buyItem2);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setBuyItemList(list);

        String json = objectMapper.writeValueAsString(createOrderRequest);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.order_id").value(2))
                .andExpect(jsonPath("$.user_id").value(1))
                .andExpect(jsonPath("$.total_amount").value(100060))
                .andExpect(jsonPath("$.created_date").isNotEmpty())
                .andExpect(jsonPath("$.last_modified_date").isNotEmpty())
                .andExpect(jsonPath("$.order_items").isNotEmpty());
    }

    @Test
    @DisplayName("createOrder-找不到userId")
    void createOrder_userIdNotFound() throws Exception {
        List<BuyItem> list = new ArrayList<>();
        BuyItem buyItem1 = new BuyItem();
        BuyItem buyItem2 = new BuyItem();
        buyItem1.setProductId(1);
        buyItem1.setQuantity(2);
        buyItem2.setProductId(4);
        buyItem2.setQuantity(1);
        list.add(buyItem1);
        list.add(buyItem2);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setBuyItemList(list);

        String json = objectMapper.writeValueAsString(createOrderRequest);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/10/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @DisplayName("createOrder-找不到productId")
    void createOrder_productIdNotFound() throws Exception {
        List<BuyItem> list = new ArrayList<>();
        BuyItem buyItem1 = new BuyItem();
        BuyItem buyItem2 = new BuyItem();
        buyItem1.setProductId(20);
        buyItem1.setQuantity(2);
        buyItem2.setProductId(21);
        buyItem2.setQuantity(1);
        list.add(buyItem1);
        list.add(buyItem2);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setBuyItemList(list);

        String json = objectMapper.writeValueAsString(createOrderRequest);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @DisplayName("createOrder-購買數量超過庫存")
    void createOrder_productStockNotEnough() throws Exception {
        List<BuyItem> list = new ArrayList<>();
        BuyItem buyItem1 = new BuyItem();
        BuyItem buyItem2 = new BuyItem();
        buyItem1.setProductId(1);
        buyItem1.setQuantity(999);
        buyItem2.setProductId(2);
        buyItem2.setQuantity(999);
        list.add(buyItem1);
        list.add(buyItem2);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setBuyItemList(list);

        String json = objectMapper.writeValueAsString(createOrderRequest);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @DisplayName("getOrders-正常取得訂單")
    void getOrders_Sucess() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1/orders");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.limit").value(5))
                .andExpect(jsonPath("$.list").isNotEmpty());
    }

    @Test
    @DisplayName("getOrders-找不到使用者id")
    void getOrders_userIdNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/999/orders");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}