package com.kether.springbootmall.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProducts() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/products")
                .param("category","CAR");
        mockMvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productList[0].product_name").value("Tesla"));
    }
    //getProductById測試
    @Test
    @DisplayName("getProductById-成功測試")
    void getProductById_Success() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/products/1");
        mockMvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.product_id").value(1))
                .andExpect(jsonPath("$.product_name").value("蘋果（澳洲）"))
                .andExpect(jsonPath("$.category").value("FOOD"));
    }

    @Test
    @DisplayName("getProductById-找不到ID測試")
    void getProductById_NotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/products/114514");
        mockMvc.perform(request)
                .andExpect(status().is(404));
    }

    //createProduct測試
    @Test
    @Transactional
    @DisplayName("createProduct-成功測試")
    void createProduct_Success() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"product_name\" : \"AveMujica\",\n" +
                        "    \"category\" : \"BOOK\",\n" +
                        "    \"image_url\" : \"https://www.e-muse.com.tw/wp-content/uploads/2024/12/BanG-Dream-Ave-Mujica_%E7%B9%81%E5%AE%98%E7%B6%B2V3.jpg\",\n" +
                        "    \"price\" : \"114\",\n" +
                        "    \"stock\" : \"514\"\n" +
                        "}"
);
        mockMvc.perform(request)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.product_name").value("AveMujica"))
                .andExpect(jsonPath("$.category").value("BOOK"));

    }

    @Test
    @Transactional
    @DisplayName("createProduct-遺漏必要參數")
    void createProduct_IllegalParameter() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"product_name\" : \"MyGO!!!!!\"\n" +
                        "}"
                );
        mockMvc.perform(request)
                .andExpect(status().is(400));
    }

    //updateProduct 測試
    @Test
    @Transactional
    @DisplayName("updateProduct-成功測試")
    void updateProduct_Sucess() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"product_name\" : \"AveMujica\",\n" +
                        "    \"category\" : \"BOOK\",\n" +
                        "    \"image_url\" : \"https://www.e-muse.com.tw/wp-content/uploads/2024/12/BanG-Dream-Ave-Mujica_%E7%B9%81%E5%AE%98%E7%B6%B2V3.jpg\",\n" +
                        "    \"price\" : \"114\",\n" +
                        "    \"stock\" : \"514\"\n" +
                        "}");
        mockMvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.product_name").value("AveMujica"))
                .andExpect(jsonPath("$.category").value("BOOK"))
                .andExpect(jsonPath("$.price").value("114"));

    }

    @Test
    @Transactional
    @DisplayName("updateProduct-ID沒找到測試")
    void updateProduct_IDNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .put("/products/114514")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"product_name\" : \"AveMujica\",\n" +
                        "    \"category\" : \"BOOK\",\n" +
                        "    \"image_url\" : \"https://www.e-muse.com.tw/wp-content/uploads/2024/12/BanG-Dream-Ave-Mujica_%E7%B9%81%E5%AE%98%E7%B6%B2V3.jpg\",\n" +
                        "    \"price\" : \"114\",\n" +
                        "    \"stock\" : \"514\"\n" +
                        "}");
        mockMvc.perform(request)
                .andExpect(status().is(404));

    }

    @Test
    @Transactional
    @DisplayName("updateProduct-遺漏必要參數")
    void updateProduct_IllegalParameter() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"product_name\" : \"AveMujica\",\n" +
                        "    \"category\" : \"BOOK\"\n" +
                        "}");
        mockMvc.perform(request)
                .andExpect(status().is(400));

    }

    //deleteProduct
    @Test
    @Transactional
    void deleteProduct_Sucess() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/products/1");
        mockMvc.perform(request)
                .andExpect(status().is(204));
    }

}