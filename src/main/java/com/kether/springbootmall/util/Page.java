package com.kether.springbootmall.util;

import com.kether.springbootmall.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page<T> {
    private Integer limit;
    private Integer offset;
    private Integer total;
    private List<T> productList;

}
