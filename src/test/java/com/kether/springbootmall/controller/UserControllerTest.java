package com.kether.springbootmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kether.springbootmall.dto.UserRequest;
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
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    //register
    @Test
    @Transactional
    @DisplayName("register-成功測試")
    void register_Success() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test1@gmail.com");
        userRequest.setPassword("test");
        String json = objectMapper.writeValueAsString(userRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.user_id").isNotEmpty())
                .andExpect(jsonPath("$.email").value("test1@gmail.com"))
                .andExpect(jsonPath("$.created_date").isNotEmpty())
                .andExpect(jsonPath("$.last_modified_date").isNotEmpty());
    }

    @Test
    @Transactional
    @DisplayName("register-錯誤email格式")
    void register_IllegalMailFormat() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test");
        userRequest.setPassword("test");
        String json = objectMapper.writeValueAsString(userRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().is(400));
    }

    @Test
    @Transactional
    @DisplayName("register-重複email")
    void register_ExistedMail() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test");
        userRequest.setPassword("test");
        String json = objectMapper.writeValueAsString(userRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().is(400));
    }

    //login
    @Test
    @Transactional
    @DisplayName("login-正常登入")
    void login_Success() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@gmail.com");
        userRequest.setPassword("test");
        String json = objectMapper.writeValueAsString(userRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.user_id").isNotEmpty())
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.created_date").isNotEmpty())
                .andExpect(jsonPath("$.last_modified_date").isNotEmpty());
    }

    @Test
    @Transactional
    @DisplayName("login-錯誤Email")
    void login_WrongEmail() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("Worng@gmail.com");
        userRequest.setPassword("test");
        String json = objectMapper.writeValueAsString(userRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().is(400));
    }

    @Test
    @Transactional
    @DisplayName("login-錯誤Password")
    void login_WrongPassword() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@gmail.com");
        userRequest.setPassword("wrongpassword");
        String json = objectMapper.writeValueAsString(userRequest);
        RequestBuilder request = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().is(400));
    }
}