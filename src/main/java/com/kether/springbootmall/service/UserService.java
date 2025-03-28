package com.kether.springbootmall.service;

import com.kether.springbootmall.dto.UserLoginRequest;
import com.kether.springbootmall.dto.UserRequest;
import com.kether.springbootmall.model.User;

public interface UserService {
    Integer register(UserRequest userRequest);
    User getUserById(Integer id);
    User login(UserLoginRequest UserLoginRequest);
}
