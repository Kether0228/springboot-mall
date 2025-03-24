package com.kether.springbootmall.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Product {
    private Integer product_id;
    private String product_name;
    private String category;
    private String image_url;
    private Integer price;
    private Integer stock;
    private String description;
    private Date created_date;
    private Date last_modified_date;
}
