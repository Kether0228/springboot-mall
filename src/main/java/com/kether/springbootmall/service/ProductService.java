package com.kether.springbootmall.service;

import com.kether.springbootmall.dto.ProductRequest;
import com.kether.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer id, ProductRequest productRequest);
    void deleteProduct(Integer id);
}
