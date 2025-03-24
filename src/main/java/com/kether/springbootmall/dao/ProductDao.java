package com.kether.springbootmall.dao;

import com.kether.springbootmall.model.Product;
import org.springframework.stereotype.Repository;


public interface ProductDao {
    Product getProductById(Integer id);
}
