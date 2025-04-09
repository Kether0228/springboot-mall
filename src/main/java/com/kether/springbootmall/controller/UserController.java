package com.kether.springbootmall.controller;

import com.kether.springbootmall.dto.UserRequest;
import com.kether.springbootmall.model.User;
import com.kether.springbootmall.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Operation(
            summary = "註冊使用者",
            description = "使用者註冊，提供帳號與密碼進行建立新帳戶。"
    )
    @PostMapping("/user/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRequest userRequest) {
        Integer id = userService.register(userRequest);
        User user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(
            summary = "使用者登入",
            description = "使用帳號與密碼登入系統。"
    )
    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserRequest UserRequest) {
        User user = userService.login(UserRequest);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
