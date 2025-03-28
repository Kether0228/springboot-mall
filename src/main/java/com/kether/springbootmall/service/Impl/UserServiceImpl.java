package com.kether.springbootmall.service.Impl;

import com.kether.springbootmall.dao.UserDao;
import com.kether.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
}
