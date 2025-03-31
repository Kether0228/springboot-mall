package com.kether.springbootmall.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {
    private Integer order_id;
    private Integer user_id;
    private Integer total_amount;
    private Date created_date;
    private Date last_modified_date;
}
