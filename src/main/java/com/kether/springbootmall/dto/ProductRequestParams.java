package com.kether.springbootmall.dto;

import com.kether.springbootmall.Constant.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestParams {
    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sort;
}
