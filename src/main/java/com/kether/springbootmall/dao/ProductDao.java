package com.kether.springbootmall.dao;

import com.kether.springbootmall.dto.ProductRequest;
import com.kether.springbootmall.model.Product;
import org.springframework.stereotype.Repository;


public interface ProductDao {
    Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer id, ProductRequest productRequest);
}
