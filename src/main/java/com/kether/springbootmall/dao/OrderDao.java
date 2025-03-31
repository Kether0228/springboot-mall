package com.kether.springbootmall.dao;

public interface OrderDao {
    Integer createOrder(Integer userId,Integer totalAmount);

}
