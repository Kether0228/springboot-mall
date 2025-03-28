package com.kether.springbootmall.dao;

import com.kether.springbootmall.dto.UserLoginRequest;
import com.kether.springbootmall.dto.UserRequest;
import com.kether.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRequest userRequest);
    User getUserByEmail(String email);
    User getUserById(Integer id);
}
