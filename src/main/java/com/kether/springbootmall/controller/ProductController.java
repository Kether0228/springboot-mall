package com.kether.springbootmall.controller;

import com.kether.springbootmall.Constant.ProductCategory;
import com.kether.springbootmall.dto.ProductRequest;
import com.kether.springbootmall.dto.ProductRequestParams;
import com.kether.springbootmall.model.Product;
import com.kether.springbootmall.service.ProductService;
import com.kether.springbootmall.util.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    @Operation(
            summary = "取得商品列表",
            description = "可以根據分類、關鍵字搜尋、排序欄位與方式、分頁查詢商品清單。"
    )
    @GetMapping("/products")
    public ResponseEntity<Page> getProducts(
            //查詢條件
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "5") @Max(100) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset

    ) {
        ProductRequestParams params = new ProductRequestParams();
        params.setCategory(category);
        params.setSearch(search);
        params.setOrderBy(orderBy);
        params.setSort(sort);
        params.setLimit(limit);
        params.setOffset(offset);
        List<Product> productList =  productService.getProducts(params);
        Integer total =  productService.countProduct(params);
        Page page = new Page();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setList(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Operation(
            summary = "取得單一商品資訊",
            description = "根據商品 ID 取得對應的商品詳細資料。"
    )
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {

        Product  product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(200).body(product);
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @Operation(
            summary = "建立商品",
            description = "新增一筆商品資料，需提交完整的商品內容。"
    )
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Operation(
            summary = "更新商品",
            description = "根據商品 ID 更新商品內容，若商品不存在回傳 404。"
    )
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId
                                                , @RequestBody @Valid ProductRequest productRequest) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(404).build();
        }
        productService.updateProduct(productId, productRequest);
        Product updatedProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @Operation(
            summary = "刪除商品",
            description = "根據商品 ID 刪除指定商品資料。"
    )
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
