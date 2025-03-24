package com.kether.springbootmall.dao.Impl;

import com.kether.springbootmall.dao.ProductDao;
import com.kether.springbootmall.model.Product;
import com.kether.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer id) {
        String sql = "SELECT * FROM product WHERE product_id = :id";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<Product> productList = namedParameterJdbcTemplate.query(
                sql,
                map,
                new ProductRowMapper()
        );

        if (productList.isEmpty()) {
            return null;
        }else{
            return productList.get(0);
        }

    }
}
