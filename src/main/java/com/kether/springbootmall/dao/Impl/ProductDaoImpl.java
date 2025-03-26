package com.kether.springbootmall.dao.Impl;

import com.kether.springbootmall.Constant.ProductCategory;
import com.kether.springbootmall.dao.ProductDao;
import com.kether.springbootmall.dto.ProductRequest;
import com.kether.springbootmall.dto.ProductRequestParams;
import com.kether.springbootmall.model.Product;
import com.kether.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id,product_name,category,image_url,price,stock,description," +
                "created_date,last_modified_date FROM product WHERE product_id = :productId";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("productId", productId);
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

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, description, created_date, last_modified_date)" +
                "VALUES (:product_name, :category, :image_url, :price, :stock, :description, :created_date, :last_modified_date)";
        Map<String,Object> map = new HashMap<>();
        map.put("product_name", productRequest.getProduct_name());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImage_url());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        return Integer.valueOf(keyHolder.getKey().intValue());
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product  SET product_name = :product_name,category = :category, " +
                "image_url = :image_url,price = :price,stock=:stock,description = :description," +
                "last_modified_date = :last_modified_date WHERE product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("id",productId);
        map.put("product_name", productRequest.getProduct_name());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImage_url());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        Date now = new Date();
        map.put("last_modified_date", now);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public List<Product> getProducts(ProductRequestParams params) {
        String sql = "SELECT product_id,product_name,category,image_url,price,stock,description," +
                "created_date,last_modified_date FROM product WHERE 1=1";
        Map<String,Object> map = new HashMap<String,Object>();
        //查詢條件
        if (params.getCategory() != null) {
            sql += " AND category = :category";
            map.put("category", params.getCategory().name());
        }

        if(params.getSearch() != null ){
            sql += " AND product_name LIKE :search";
            map.put("search", "%" + params.getSearch() + "%");
        }

        //排序
        sql += " ORDER BY " + params.getOrderBy() + " " + params.getSort();


        return namedParameterJdbcTemplate.query(sql, map , new ProductRowMapper());
    }


}
