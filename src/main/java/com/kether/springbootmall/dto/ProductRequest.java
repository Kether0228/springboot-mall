package com.kether.springbootmall.dto;

import com.kether.springbootmall.Constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductRequest {
    @NotNull
    private String product_name;
    @NotNull
    private ProductCategory category;
    @NotNull
    private String image_url;
    @NotNull
    private Integer price;
    @NotNull
    private Integer stock;

    private String description;

}
