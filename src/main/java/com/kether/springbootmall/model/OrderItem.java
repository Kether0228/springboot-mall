package com.kether.springbootmall.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    Integer order_item_id;
    Integer order_id;
    Integer product_id;
    Integer quantity;
    Integer amount;
    String product_name;
    String image_url;
}
