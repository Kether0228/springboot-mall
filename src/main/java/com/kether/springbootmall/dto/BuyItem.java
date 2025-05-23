package com.kether.springbootmall.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyItem {
    @NotNull
    private Integer productId;
    @NotNull
    @Min(0)
    private Integer quantity;
}
