package com.kether.springbootmall.service.Impl;

import com.kether.springbootmall.dao.ProductDao;
import com.kether.springbootmall.model.Product;
import com.kether.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer id) {

        return productDao.getProductById(id);
    }
}
