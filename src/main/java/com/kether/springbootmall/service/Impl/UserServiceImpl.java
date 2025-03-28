package com.kether.springbootmall.service.Impl;

import com.kether.springbootmall.dao.UserDao;
import com.kether.springbootmall.dto.UserLoginRequest;
import com.kether.springbootmall.dto.UserRequest;
import com.kether.springbootmall.model.User;
import com.kether.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Integer register(UserRequest userRequest) {
        //檢查email是否被註冊
        User user = userDao.getUserByEmail(userRequest.getEmail());
        if (user != null) {
            log.warn("此 email {} 已被註冊", userRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //註冊帳號
        return userDao.createUser(userRequest);
    }

    @Override
    public User getUserById(Integer id) {

        return userDao.getUserById(id);
    }

    @Override
    public User login(UserLoginRequest UserLoginRequest) {
        User user = userDao.getUserByEmail(UserLoginRequest.getEmail());
        if (user == null) {
            log.warn("此email {} 尚未註冊", UserLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!user.getPassword().equals(UserLoginRequest.getPassword())) {
            log.warn("email {} 密碼輸入錯誤", UserLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return user;
        }
    }


}
